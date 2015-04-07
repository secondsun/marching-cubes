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
package net.saga.marchingcubes.window;

import static com.jogamp.opengl.GL.GL_COLOR_BUFFER_BIT;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import net.saga.marchingcubes.impl.VoxelGrid;

/**
 *
 * @author summers
 */
public class Game implements GLEventListener {

    private GL2 gl;

    private final VoxelGrid grid = new VoxelGrid(40);
    
    @Override
    public void init(GLAutoDrawable drawable) {
        gl = (GL2) drawable.getGL();
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrtho(0,1.1f, 0, 1.1f, -1, 1);
        gl.glMatrixMode(GL2.GL_MODELVIEW);

        gl.glViewport(0, 0, 800, 600);
        gl.glClearColor(0, 0, 0, 0);
        gl.glClearColor(0, 0, 0, 0);

    }

    @Override
    public void dispose(GLAutoDrawable drawable) {

    }

    @Override
    public void display(GLAutoDrawable drawable) {

        gl.glClear(GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();
        grid.render(gl);
        
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

    }

}
