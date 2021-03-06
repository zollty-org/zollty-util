/* 
 * Copyright (C) 2014-2015 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by ZollTy on 2015-3-17 (http://blog.zollty.com/, zollty@163.com)
 */
package org.jretty.util.support;

import static org.jretty.util.ExceptionTestTools.CONTROLLER_ALERT;
import static org.jretty.util.ExceptionTestTools.MSG_SPLIT;
import static org.jretty.util.ExceptionTestTools.SERVICE_ALERT;
import static org.jretty.util.ExceptionTestTools.UNDER_UNKNOWN_EXCEPTION;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.jretty.log.LogFactory;
import org.jretty.log.Logger;
import org.jretty.util.NestedCheckedException;
import org.jretty.util.NestedException;
import org.jretty.util.NestedIOException;

/**
 * @author zollty
 * @since 2015-3-17
 */
public class NestedExceptionDelegateTest {
    
    private static final Logger LOG = LogFactory.getLogger();
    
    @Test
    public void testAll() {

        try {
            throw new NestedCheckedException(new NestedCheckedException(new NestedCheckedException(CONTROLLER_ALERT)));
        }
        catch (Exception ne) {
            
//            DebugTool.println(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT);
//            DebugTool.error(((NestedCheckedException) ne).getStackTraceStr());

            Assert.assertEquals(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT,
                    ne.toString());

            Assert.assertEquals(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT,
                    ((NestedException) ne).getStackTraceStr());
        }

        try {
            doController();
        }
        catch (Exception ne) {
            
//            DebugTool.printStack(ne);

            Assert.assertTrue(ne.getStackTrace()[0].toString().startsWith(this.getClass().getName() + ".underService"));

            Assert.assertEquals(IOException.class.getName()+": " + CONTROLLER_ALERT + MSG_SPLIT + SERVICE_ALERT + MSG_SPLIT
                    + UNDER_UNKNOWN_EXCEPTION, ne.toString());

        }
    }

    private void doController() throws NestedCheckedException {
        try {
            doService();
        }
        catch (Exception ioe) {
            throw new NestedCheckedException(ioe, CONTROLLER_ALERT);
        }
    }

    private void doService() throws NestedCheckedException {
        try {
            underService();
        }
        catch (IOException ioe) {
            throw new NestedCheckedException(ioe, SERVICE_ALERT);
        }
    }

    private void underService() throws IOException {
        throw new IOException(UNDER_UNKNOWN_EXCEPTION);
    }

    
    @Test
    public void testAll1() {

        try {
            throw new NestedCheckedException(new NestedCheckedException(new NestedIOException(CONTROLLER_ALERT)));
        }
        catch (Exception ne) {
            
//            DebugTool.println(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT);
//            DebugTool.error(((NestedCheckedException) ne).getStackTraceStr());

            // 以下两句代码无需更改
            Assert.assertEquals(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT,
                    ne.toString());
            Assert.assertEquals(((ExceptionDelegateSupport) ne).getDelegate().getExceptionName() + CONTROLLER_ALERT,
                    ((NestedCheckedException) ne).getStackTraceStr());

        }

        try {
            doController1();
        }
        catch (Exception ne) {
            
//            DebugTool.printStack(ne);
            
            LOG.info(ne.toString());

            Assert.assertTrue(ne.getStackTrace()[0].toString().startsWith(this.getClass().getName() + ".underService"));

            Assert.assertEquals(IOException.class.getName() + ": " + CONTROLLER_ALERT + MSG_SPLIT + SERVICE_ALERT
                    + MSG_SPLIT + UNDER_UNKNOWN_EXCEPTION, ne.toString());

        }
    }

    private void doController1() throws NestedCheckedException {
        try {
            doService1();
        }
        catch (Exception ioe) {
            throw new NestedCheckedException(ioe, CONTROLLER_ALERT);
        }
    }

    private void doService1() throws NestedCheckedException {
        try {
            underService1();
        }
        catch (IOException ioe) {
            throw new NestedCheckedException(ioe, SERVICE_ALERT);
        }
    }

    private void underService1() throws IOException {
        throw new IOException(UNDER_UNKNOWN_EXCEPTION);
    }
}
