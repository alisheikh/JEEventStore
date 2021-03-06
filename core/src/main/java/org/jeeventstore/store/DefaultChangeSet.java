/*
 * Copyright (c) 2013 Red Rainbow IT Solutions GmbH, Germany
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.jeeventstore.store;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jeeventstore.ChangeSet;

/**
 * Default implementation of a change set.
 */
public class DefaultChangeSet implements ChangeSet {

    private String bucketId;
    private String streamId;
    private long streamVersion;
    private String changeSetId;
    private List<Serializable> events;

    public DefaultChangeSet(
            String bucketId,
            String streamId,
            long streamVersion,
            String changeSetId,
            List<? extends Serializable> events) {

        if (bucketId == null)
            throw new NullPointerException("bucketId must not be null");
        if (streamId == null)
            throw new NullPointerException("streamId must not be null");
        if (streamVersion < 0)
            throw new IllegalArgumentException("streamVersion must not be negative");
        if (changeSetId == null)
            throw new NullPointerException("changeSetId must not be null");
        if (events == null)
            throw new NullPointerException("events must not be null");

        this.bucketId = bucketId;
        this.streamId = streamId;
        this.streamVersion = streamVersion;
        this.changeSetId = changeSetId;
        this.events = Collections.unmodifiableList(new ArrayList<>(events));
    }

    @Override
    public String bucketId() {
        return bucketId;
    }

    @Override
    public String streamId() {
        return streamId;
    }

    @Override
    public long streamVersion() {
        return streamVersion;
    }

    @Override
    public String changeSetId() {
        return changeSetId;
    }

    @Override
    public Iterator<Serializable> events() {
        return this.events.iterator();
    }

}
