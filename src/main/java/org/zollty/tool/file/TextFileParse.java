/* 
 * Copyright (C) 2013-2015 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by Zollty Tsou on 2013-08-02 [http://blog.zollty.com]
 */
package org.zollty.tool.file;

/**
 * 将文本文件内容解析、封装成相应的对象。
 * 
 * @author zollty
 * @date 2013-8-2
 */
public interface TextFileParse<T> {

    public T parseOneLine(String line);

}