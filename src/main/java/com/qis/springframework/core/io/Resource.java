package com.qis.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 获取资源流
 *
 * @author: qishuo
 * @date: 2022/12/8 15:16
 */
public interface Resource {

    /**
     * 获取输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
