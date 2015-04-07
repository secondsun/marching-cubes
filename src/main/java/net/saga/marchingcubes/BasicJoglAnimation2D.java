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

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import static jogamp.opengl.util.av.EGLMediaPlayerImpl.TextureType.GL;

/**
 * A very simple OpenGL animation, using a Timer to drive the
 * animation and a rotation transform to rotate a picture.
 */
public class BasicJoglAnimation2D extends JPanel implements GLEventListener {

   public static void main(String[] args) {
      JFrame window = new JFrame("Basic JOGL Animation 2D");
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setContentPane(new BasicJoglAnimation2D());
      window.pack();
      window.setVisible(true);
   }
   
   private GLJPanel drawable;  // The OpenGL display panel.
   private int frameNumber;    // Current frame number, increases by 1 in each frame.
   
   public BasicJoglAnimation2D() {
      drawable = new GLJPanel();
      drawable.setPreferredSize(new Dimension(600,600));
      drawable.addGLEventListener(this);
      setLayout(new BorderLayout());
      add(drawable, BorderLayout.CENTER);
      Timer timer = new Timer(30, new ActionListener() {
         public void actionPerformed(ActionEvent evt) {
            frameNumber++;
            drawable.repaint();
         }
      });
      timer.setInitialDelay(1000);
      timer.start();
   }

   /**
    * This method is called when the GLJPanel is created.  It initializes
    * the GL context.  Here, it sets the clear color to be light blue.
    */
   public void init(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClearColor(0.8f, 0.8f, 1, 1);
   }

   /**
    * This method is called when the GLJPanel needs to be redrawn.  Here,
    * a yellow triangle, outlined in black, is drawn with a rotation 
    * equal to the frame number (measured in degrees).
    */
   public void display(GLAutoDrawable drawable) {
      GL2 gl = drawable.getGL().getGL2();
      gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
      gl.glLoadIdentity();
      gl.glRotated(frameNumber, 0, 0, 1);  // Rotation to be applied to the triangle.
      gl.glColor3f(1.0f, 1.0f, 0.0f);
      gl.glBegin(GL2.GL_POLYGON);
      gl.glVertex2f(-0.5f,-0.3f);
      gl.glVertex2f(0.5f,-0.3f);
      gl.glVertex2f(0,0.6f);
      gl.glEnd();
      gl.glColor3f(0,0,0);
      gl.glBegin(GL2.GL_LINE_LOOP);
      gl.glVertex2f(-0.5f,-0.3f);
      gl.glVertex2f(0.5f,-0.3f);
      gl.glVertex2f(0,0.6f);
      gl.glEnd();
   }

   public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
   }

   public void displayChanged(GLAutoDrawable drawable, boolean modeChanged,
         boolean deviceChanged) {
   }

    @Override
    public void dispose(GLAutoDrawable drawable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}