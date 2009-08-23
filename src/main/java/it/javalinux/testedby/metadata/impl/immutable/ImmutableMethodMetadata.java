/*
 * Stefano Maestri, javalinuxlabs.org Copyright 2008, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors. 
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package it.javalinux.testedby.metadata.impl.immutable;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import it.javalinux.testedby.metadata.MethodMetadata;

public class ImmutableMethodMetadata implements MethodMetadata {

    private static final long serialVersionUID = 1L;
    
    private final String name;
    private final String[] parameterTypes;

    public ImmutableMethodMetadata(String name, String[] parameterTypes) {
	this.name = name;
	if (parameterTypes == null) {
	    this.parameterTypes = new String[0];
	} else {
	    this.parameterTypes = parameterTypes;
	}
    }

    public ImmutableMethodMetadata(Method method) {
	this.name = method.getName();
	List<String> l = new LinkedList<String>();
	for (Class<?> c : method.getParameterTypes()) {
	    l.add(c.getName());
	}
	if (l.isEmpty()) {
	    this.parameterTypes = new String[0];
	} else {
	    this.parameterTypes = (String[])l.toArray(new String[l.size()]);
	}
    }

    public String getName() {
	return name;
    }

    public String[] getParameterTypes() {
	return parameterTypes;
    }
    
    public boolean equals(Object o) {
	if (!(o instanceof MethodMetadata)) {
	    return false;
	}
	MethodMetadata obj = (MethodMetadata)o;
	//name check
	boolean nameCheck;
	if (name == null) {
	    if (obj.getName() != null) {
		return false;
	    } else {
		nameCheck = true;
	    }
	} else {
	    nameCheck = name.equals(obj.getName());
	}
	return nameCheck && Arrays.deepEquals(parameterTypes, obj.getParameterTypes());
    }

    public int hashCode() {
	return 31 * (name.hashCode() + Arrays.deepHashCode(parameterTypes));
    }
}