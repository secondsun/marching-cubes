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

/**
 *
 * @author summers
 */
public class Vector3f {
    
    private static final int X = 0;
    private static final int Y = 1;
    private static final int Z = 2;
    
    public static Vector3f UNIT = new Vector3f(1f,1f,1f);

    private final float[] vector;
    
    public Vector3f(float x, float y, float z) {
        vector = new float[]{x,y,z};
    }
    
    public float[] vector() {
        return new float[]{vector[X], vector[Y], vector[Z] };
    }

    public Vector3f scale(float scalar) {
        return new Vector3f(vector[X] * scalar, vector[Y] * scalar, vector[Z] * scalar );
    }

    public float x() {
        return vector[X];
    }
    
    public float y() {
        return vector[Y];
    }
    public float z() {
        return vector[Z];
    }
    
}
