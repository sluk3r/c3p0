/*
 * Distributed as part of c3p0 v.0.9.1.2
 *
 * Copyright (C) 2005 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License version 2.1, as 
 * published by the Free Software Foundation.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this software; see the file LICENSE.  If not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA 02111-1307, USA.
 */


package com.mchange.lang;

import java.io.*;

public final class ThrowableUtils {
    public static String extractStackTrace(Throwable t) {
        StringWriter me = new StringWriter();
        PrintWriter pw = new PrintWriter(me);
        t.printStackTrace(pw);
        pw.flush();
        return me.toString();
    }

    public static boolean isChecked(Throwable t) {
        return
                t instanceof Exception &&
                        !(t instanceof RuntimeException);
    }

    public static boolean isUnchecked(Throwable t) {
        return !isChecked(t);
    }

    private ThrowableUtils() {
    }
}
