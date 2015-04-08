/**
 * Copyright (C) 2015 Summers Pittman (secondsun@gmail.com)
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA
 */
package net.saga.marchingcubes.voxel;

import com.jogamp.newt.event.MouseEvent;
import net.saga.marchingcubes.core.Vector3f;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.FloatUtil;
import com.jogamp.opengl.math.Matrix4;
import java.util.Arrays;
import net.saga.marchingcubes.core.BaseGraphicsObject;
import net.saga.marchingcubes.core.Event;
import net.saga.marchingcubes.window.Game;

/**
 *
 * @author summers
 */
public class VoxelGrid extends BaseGraphicsObject {

    public final int resolution;

    private final VoxelObject[] voxels;
    private final float voxelSize;

    public VoxelGrid(int resolution, float size) {
        this.resolution = resolution;
        voxelSize = size / resolution;
        voxels = new VoxelObject[resolution * resolution];

        int i = 0;

        for (int y = 0; y < resolution; y++) {
            for (int x = 0; x < resolution; x++) {
                createVoxel(i, x, y);
                ++i;
            }
        }

    }

    private void createVoxel(int i, int x, int y) {
        VoxelObject o = new VoxelObject();
        o.transform = new Matrix4();
        o.localPosition = new Vector3f((x + 0.5f) * voxelSize, (y + 0.5f) * voxelSize, 0f);
        o.localScale = Vector3f.UNIT.scale(voxelSize);
        voxels[i] = o;
    }

    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();
        gl.glMultMatrixf(transform.getMatrix(), 0);
        gl.glTranslatef(localPosition.x(), localPosition.y(), 0);
        gl.glScalef(localScale.x(), localScale.y(), 1);
        for (VoxelObject o : voxels) {
            o.render(gl);
        }
        gl.glPopMatrix();
    }

    @Override
    public void update() {
        MouseEvent event = Event.INSTANCE.event;
        if (event != null && event.isButtonDown(MouseEvent.BUTTON1)) {
            float winX = event.getX();
            float winY = event.getY();

            System.out.println("Clicked [" + winX + ", " + winY + "]");

            // viewportOriginX, viewportOriginY, viewportWidth, viewportHeight
            float[] viewPort = {0, 0, 800, 600};

            float[] objPos = new float[4];  // out parameter.

            // The camera"s model-view matrix is the result of gluLookAt.
            Matrix4 modelViewMatrix = Game.MODELVIEW_MATRIX;

            // The perspective matrix is the result of gluPerspective.
            Matrix4 perspectiveMatrix = Game.CAMERA.viewMatrix();

            // Ray start
            boolean result1 = gluUnProject(
                    winX, winY, 0.0f,
                    modelViewMatrix, perspectiveMatrix,
                    viewPort, objPos);
            System.out.println(
                    "Seg start: " + Arrays.toString(objPos) + " (result:" + result1 + ")"
            );

            // Ray end
            boolean result2 = gluUnProject(
                    winX, winY, 1.0f,
                    modelViewMatrix, perspectiveMatrix,
                    viewPort, objPos);
            System.out.println(
                    "Seg end: " +  Arrays.toString(objPos) + " (result:" + result2 + ")"
            );
            Event.INSTANCE.consume(event);
        }
    }

    private boolean gluUnProject(float winX, float winY, float winZ, Matrix4 modelViewMatrix, Matrix4 projectionMatrix, float[] viewPort, float[] objPos) {
        Matrix4 transformMatrix = new Matrix4();
        transformMatrix.multMatrix(projectionMatrix);
        transformMatrix.multMatrix(modelViewMatrix);
        transformMatrix.invert();

        // Transformation of normalized coordinates (-1 to 1).
        /**
         * @type {Array.<Number>}
         */
        float[] inVector = {
            (winX - viewPort[0]) / viewPort[2] * 2.0f - 1.0f,
            (winY - viewPort[1]) / viewPort[3] * 2.0f - 1.0f,
            2.0f * winZ - 1.0f,
            1.0f};

        // Now transform that vector into object coordinates.
        /**
         * @type {goog.math.Matrix}
         */
        // Flip 1x4 to 4x1. (Alternately use different matrix ctor.
        Matrix4 inMatrix = new Matrix4();
        inMatrix.getMatrix()[0] = inVector[0];
        inMatrix.getMatrix()[4] = inVector[1];
        inMatrix.getMatrix()[8] = inVector[2];
        inMatrix.getMatrix()[12] = inVector[3];
        /**
         * @type {goog.math.Matrix}
         */
        Matrix4 resultMtx = new Matrix4();
        resultMtx.multMatrix(transformMatrix);
        resultMtx.multMatrix(inMatrix);
        /**
         * @type {Array.<Number>}
         */
        float[] resultArr = {
            resultMtx.getMatrix()[0],
            resultMtx.getMatrix()[4],
            resultMtx.getMatrix()[8],
            resultMtx.getMatrix()[12]};

        if (resultArr[3] == 0.0f) {
            return false;
        }

        // Invert to normalize x, y, and z values.
        resultArr[3] = 1.0f / resultArr[3];

        objPos[0] = resultArr[0] * resultArr[3];
        objPos[1] = resultArr[1] * resultArr[3];
        objPos[2] = resultArr[2] * resultArr[3];

        return true;
    }

}
