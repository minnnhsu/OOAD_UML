package UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import mode.*;
import shape.*;

public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 696;
	private static final int HEIGHT = 457;

	private static Canvas instance;
	private List<Obj> objects = new ArrayList<>();
	private List<Line> lines = new ArrayList<>();
	private List<Obj> selectedObjs = new ArrayList<>();
	private Line currentLine = null;
	private Rectangle selectionRect;
	private ButtonType type = ButtonType.SELECTION;

	private Mode currentMode;
	private Map<ButtonType, Mode> modeMap = new HashMap<>();

	private Canvas() {
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public void initialize() {
		initializeModes();
		setMode(type);
	}

	public static synchronized Canvas getInstance() {
		if (instance == null) {
			instance = new Canvas();
		}
		return instance;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (objects.size() > 0) {
			for (Obj obj : objects) {
				if (obj != null) {
					obj.draw(g);
				}
			}
		}
		if (selectedObjs.size() > 0) {
			for (Obj obj : selectedObjs) {
				if (obj != null) {
					obj.drawSelected(g);
				}
			}
		}
		if (currentLine != null) {
			currentLine.draw(g);
		}
		if (lines.size() > 0) {
			for (Line line : lines) {
				line.draw(g);
			}
		}
		if (selectionRect != null) {
			g.setColor(new Color(100, 200, 255, 100));
			g.drawRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);
			g.setColor(new Color(100, 200, 255, 50));
			g.fillRect(selectionRect.x, selectionRect.y, selectionRect.width, selectionRect.height);
		}
	}

	public void addObj(Obj object) {
		objects.add(object);
		repaint();
	}

	public List<Obj> getObjests() {
		return objects;
	}

	public void addLine(Line line) {
		lines.add(line);
		repaint();
	}

	public void setCurrentLine(Line line) {
		currentLine = line;
	}

	public ButtonType getType() {
		return type;
	}

	public void setSelectedObjects(List<Obj> selectedObjs) {
		this.selectedObjs = selectedObjs;
	}

	public List<Obj> getSelectedObjects() {
		return selectedObjs;
	}

	public void setMode(ButtonType type) {
		this.type = type;
		Mode mode = modeMap.get(type);
		if (mode != null) {
			setCurrentMouseListener(mode);
		} else {
			System.err.println("Type " + type + " is not recognized. No mode set.");
		}
	}

	private void setCurrentMouseListener(Mode listener) {
		if (currentMode != null) {
			removeMouseListener(currentMode);
			removeMouseMotionListener(currentMode);
		}
		currentMode = listener;
		addMouseListener(currentMode);
		addMouseMotionListener(currentMode);
	}

	private void initializeModes() {
		modeMap.put(ButtonType.SELECTION, new SelectMode(this));
		modeMap.put(ButtonType.ASSOCIATION, new LineMode(LineType.ASSOCIATION, this));
		modeMap.put(ButtonType.GENERALIZATION, new LineMode(LineType.GENERALIZATION, this));
		modeMap.put(ButtonType.COMPOSITION, new LineMode(LineType.COMPOSITION, this));
		modeMap.put(ButtonType.CLASS, new ObjMode(ObjType.CLASS, this));
		modeMap.put(ButtonType.USECASE, new ObjMode(ObjType.USECASE, this));
	}

	public void drawSelectWindow(Point startPoint, Point endPoint) {
		int x = Math.min(startPoint.x, endPoint.x);
		int y = Math.min(startPoint.y, endPoint.y);
		int width = Math.abs(startPoint.x - endPoint.x);
		int height = Math.abs(startPoint.y - endPoint.y);
		selectionRect = new Rectangle(x, y, width, height);
		repaint();
	}

	public void clearSelection() {
		selectionRect = null;
		repaint();
	}

	public void addGroup() {
		System.out.println("group " + selectedObjs.size());
		if (selectedObjs.size() > 1) {
			Group temp = new Group(selectedObjs);
			objects.add(temp);
			for (Obj obj : selectedObjs) {
				objects.remove(obj);
			}
			selectedObjs = new ArrayList<Obj>(Arrays.asList(temp));
			repaint();
		}
	}

	public void removeGroup() {
		System.out.println("ungroup " + selectedObjs.size());
		Group removeGroup = (Group) selectedObjs.get(0);
		objects.addAll(removeGroup.getObjects());
		objects.remove(removeGroup);
		selectedObjs = removeGroup.getObjects();
		repaint();
	}

	public void changeObjName(String text) {
		if (selectedObjs.get(selectedObjs.size() - 1) instanceof BasicObj) {
			BasicObj temp = (BasicObj) selectedObjs.get(selectedObjs.size() - 1);
			temp.setObjName(text);
		}
		repaint();
	}
}
