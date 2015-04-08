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
package net.saga.marchingcubes.core;

import com.jogamp.opengl.math.Matrix4;

/**
 *
 * @author summers
 */
public class Camera {

    public final float left, right, top, bottom, near, far;

    public Camera(float left, float right, float bottom, float top, float near, float far) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
        this.near = near;
        this.far = far;
    }

    public Matrix4 viewMatrix() {
        Matrix4 m = new Matrix4();
        m.makeOrtho(left, right, bottom, top, near, near);
        return m;
    }

}
