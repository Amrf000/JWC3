package com.hiveworkshop.wc3.gui.modeledit.newstuff.manipulator;

import java.awt.geom.Point2D.Double;

import com.hiveworkshop.wc3.gui.modeledit.CoordinateSystem;
import com.hiveworkshop.wc3.gui.modeledit.UndoAction;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.ModelEditor;
import com.hiveworkshop.wc3.gui.modeledit.newstuff.actions.editor.RotateAction;
import com.hiveworkshop.wc3.gui.modeledit.selection.SelectionView;
import com.hiveworkshop.wc3.mdl.Vertex;

public class RotateVerticalManipulator extends AbstractManipulator {
	private final ModelEditor modelEditor;
	private final SelectionView selectionView;

	public RotateVerticalManipulator(final ModelEditor modelEditor, final SelectionView selectionView) {
		this.modelEditor = modelEditor;
		this.selectionView = selectionView;
	}

	@Override
	public void update(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		final Vertex center = selectionView.getCenter();
		final double radians = computeRotateRadians(mouseStart, mouseEnd, center, dim1, dim2);
		modelEditor.rawRotate2d(center.x, center.y, center.z, radians, CoordinateSystem.Util.getUnusedXYZ(dim1, dim2),
				dim2);
	}

	@Override
	public UndoAction finish(final Double mouseStart, final Double mouseEnd, final byte dim1, final byte dim2) {
		update(mouseStart, mouseEnd, dim1, dim2);
		final Vertex center = selectionView.getCenter();
		final double radians = computeRotateRadians(activityStart, mouseEnd, center, dim1, dim2);
		return new RotateAction(modelEditor, center, radians, CoordinateSystem.Util.getUnusedXYZ(dim1, dim2), dim2);
	}

	private double computeRotateRadians(final Double startingClick, final Double endingClick, final Vertex center,
			final byte portFirstXYZ, final byte portSecondXYZ) {
		final double radius = selectionView.getCircumscribedSphereRadius(center);
		final double deltaAngle = (endingClick.y - startingClick.y) / radius;
		return deltaAngle;
	}

}
