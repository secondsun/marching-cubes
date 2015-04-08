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

import com.jogamp.newt.event.MouseAdapter;
import com.jogamp.newt.event.MouseEvent;
import com.jogamp.newt.event.MouseListener;

/**
 *
 * @author summers
 */
public class Event {
    
    public static final Event INSTANCE = new Event();
    
    public MouseEvent event;
    public final MouseListener listener = new MouseAdapter() {

        @Override
        public void mouseClicked(MouseEvent e) {
            event = e;
        }
        
    };


    private Event(){}

    public void consume(MouseEvent event) {
        this.event = null;
    }
    
}
