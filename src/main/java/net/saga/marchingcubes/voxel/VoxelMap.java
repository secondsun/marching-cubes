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

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLDrawable;
import com.jogamp.opengl.math.Matrix4;
import net.saga.marchingcubes.core.BaseGraphicsObject;
import net.saga.marchingcubes.core.Vector3f;

/**
 *
 * @author summers
 */
public class VoxelMap extends BaseGraphicsObject {

    private float size = 2f;

    private int voxelResolution = 8;
    private int chunkResolution = 2;

    private VoxelGrid voxelGridPrefab;

    private VoxelGrid[] chunks;

    private float chunkSize, voxelSize, halfSize;

    public VoxelMap() {
        halfSize = size * 0.5f;
        chunkSize = size / chunkResolution;
        voxelSize = chunkSize / voxelResolution;

        chunks = new VoxelGrid[chunkResolution * chunkResolution];
        for (int i = 0, y = 0; y < chunkResolution; y++) {
            for (int x = 0; x < chunkResolution; x++, i++) {
                CreateChunk(i, x, y);
            }
        }
    }

    private void CreateChunk(int i, int x, int y) {
        VoxelGrid chunk = new VoxelGrid(voxelResolution, chunkSize);
        
        chunk.localPosition = new Vector3f(x * chunkSize - halfSize, y * chunkSize - halfSize, 0);
        chunk.localScale = new Vector3f(.5f, .5f, 0);
        chunks[i] = chunk;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public int getVoxelResolution() {
        return voxelResolution;
    }

    public void setVoxelResolution(int voxelResolution) {
        this.voxelResolution = voxelResolution;
    }

    public int getChunkResolution() {
        return chunkResolution;
    }

    public void setChunkResolution(int chunkResolution) {
        this.chunkResolution = chunkResolution;
    }

    public VoxelGrid getVoxelGridPrefab() {
        return voxelGridPrefab;
    }

    public void setVoxelGridPrefab(VoxelGrid voxelGridPrefab) {
        this.voxelGridPrefab = voxelGridPrefab;
    }

    @Override
        public void render(GLAutoDrawable drawable) {
        GL2 gl = (GL2) drawable.getGL();
        gl.glPushMatrix();

        //gl.glMultMatrixf(transform.getMatrix(), 0);
        gl.glTranslatef(localPosition.x(), localPosition.y(), 0);
        gl.glScalef(localScale.x(), localScale.y(), 1);
        for (VoxelGrid chunk : chunks) {
            chunk.render(drawable);
        }
        gl.glPopMatrix();
    }

    @Override
    public void update(GLAutoDrawable drawable) {
        for (VoxelGrid chunk : chunks) {
            chunk.update(drawable);
        }
    }
    
    

}
