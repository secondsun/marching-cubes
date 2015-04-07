/*
 * Copyright (C) 2015 summers.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package net.saga.marchingcubes.impl;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.math.FloatUtil;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.math.VectorUtil;

/**
 *
 * @author summers
 */
public class VoxelGrid {

    public final int resolution;

    private final VoxelObject[] voxels;
    private final float voxelSize;

    public VoxelGrid(int resolution) {
        this.resolution = resolution;
        voxelSize = 1f / resolution;
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

    public void render(GL2 gl) {
        for (VoxelObject o : voxels) {
            o.render(gl);
        }
    }
}
