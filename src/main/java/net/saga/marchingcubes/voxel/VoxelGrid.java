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

import net.saga.marchingcubes.core.Vector3f;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.Matrix4;
import net.saga.marchingcubes.core.BaseGraphicsObject;

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
        o.transform  = new Matrix4();
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
}
