/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.felix.http.base.internal.whiteboard.tracker;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;

public abstract class AbstractTracker<T> extends ServiceTracker
{
    public AbstractTracker(final BundleContext context, final Filter filter)
    {
        super(context, filter, null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final Object addingService(final ServiceReference ref)
    {
        T service = (T) super.addingService(ref);
        added(service, ref);
        return service;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void modifiedService(final ServiceReference ref, final Object service)
    {
        modified( (T)service, ref);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void removedService(final ServiceReference ref, final Object service)
    {
        super.removedService(ref, service);
        removed((T) service, ref);
    }

    protected abstract void modified(T service, ServiceReference ref);

    protected abstract void added(T service, ServiceReference ref);

    protected abstract void removed(T service, ServiceReference ref);
}