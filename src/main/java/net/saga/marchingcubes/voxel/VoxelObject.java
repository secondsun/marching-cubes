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
import net.saga.marchingcubes.core.BaseGraphicsObject;

/**
 *
 * @author summers
 */
public class VoxelObject extends BaseGraphicsObject{

    
    public Vector3f color = new Vector3f((float) (Math.random() ),(float) (Math.random() ), (float) Math.random() );
    
    @Override
    public void render(GL2 gl) {
        gl.glPushMatrix();

        gl.glMultMatrixf(transform.getMatrix(), 0);
        gl.glTranslatef(localPosition.x(), localPosition.y(), 0);
        gl.glScalef(localScale.x(), localScale.y(), 1);
        gl.glColor3f(color.x(), color.y(), color.z());
        gl.glBegin(GL2.GL_TRIANGLES);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(0, 1, 0);
        gl.glVertex3f(1, 1, 0);
        gl.glVertex3f(0, 0, 0);
        gl.glVertex3f(1, 0, 0);
        gl.glVertex3f(1, 1, 0);
        gl.glEnd();
        gl.glPopMatrix();
    }

}
