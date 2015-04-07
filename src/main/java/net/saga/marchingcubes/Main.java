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
package net.saga.marchingcubes;

import net.saga.marchingcubes.window.Game;
import com.jogamp.nativewindow.AbstractGraphicsDevice;
import com.jogamp.nativewindow.WindowClosingProtocol;
import com.jogamp.newt.event.KeyAdapter;
import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.WindowAdapter;
import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLDrawableFactory;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.util.Animator;
import com.jogamp.opengl.util.FPSAnimator;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Launches a new GLWindow
 *
 * @author summers
 */
public class Main {

    public static void main(String args[]) {
        AtomicBoolean running = new AtomicBoolean(true);
        GLProfile.initSingleton();

        GLDrawableFactory factory = GLDrawableFactory.getDesktopFactory();
        AbstractGraphicsDevice device = factory.getDefaultDevice();
        GLWindow window = GLWindow.create(new GLCapabilities(GLProfile.getDefault()));
        window.setVisible(true);
        window.setDefaultCloseOperation(WindowClosingProtocol.WindowClosingMode.DISPOSE_ON_CLOSE);
        window.addWindowListener(new WindowAdapter() {

            @Override
            public void windowDestroyed(WindowEvent e) {
                System.exit(0);
            }
            
        });
        window.setSize(800, 600);

        window.addGLEventListener(new Game());

        FPSAnimator animator = new FPSAnimator(window, 60);
        animator.start();
    }

}
