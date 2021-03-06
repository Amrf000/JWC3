package com.hiveworkshop.wc3.gui.modeledit.activity;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

import com.hiveworkshop.wc3.gui.modeledit.CoordinateSystem;
import com.hiveworkshop.wc3.gui.modeledit.actions.newsys.ModelChangeListener;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.listener.ModelEditorChangeListener;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionListener;

public interface ViewportActivity extends SelectionListener, ModelChangeListener, ModelEditorChangeListener {
	void viewportChanged(CursorManager cursorManager);

	void mousePressed(MouseEvent e, CoordinateSystem coordinateSystem);

	void mouseReleased(MouseEvent e, CoordinateSystem coordinateSystem);

	void mouseMoved(MouseEvent e, CoordinateSystem coordinateSystem);

	void mouseDragged(MouseEvent e, CoordinateSystem coordinateSystem);

	void render(Graphics2D g, CoordinateSystem coordinateSystem);

	boolean isEditing();
}
