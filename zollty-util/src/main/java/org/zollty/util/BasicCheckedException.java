/* @(#)BasicCheckedException.java 
 * Copyright (C) 2013-2014 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by zollty on 2013-7-05 [http://blog.csdn.net/zollty (or GitHub)]
 */
package org.zollty.util;

/**
 * @author zollty 
 * @since 2013-7-05
 */
public class BasicCheckedException extends Exception {

    private static final long serialVersionUID = 6575977961100372966L;

    public BasicCheckedException() {
        super();
    }

    public BasicCheckedException(String message, String... args) {
        super(StringUtils.replaceParams(message, args));
    }

    public BasicCheckedException(Throwable arg0) {
        super(arg0);
    }

    public BasicCheckedException(Throwable e, String message, String... args) {
        super(StringUtils.replaceParams(message, args), e);
    }

}