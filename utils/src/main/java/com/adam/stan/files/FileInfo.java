package com.adam.stan.files;

import java.io.Serializable;

/***
 * TODO: consider Character sets!!!
 * 
 * @author Adam
 *
 */
public class FileInfo implements Serializable {

    private static final long serialVersionUID = 9086172223242705797L;
    private String relativePath;
    private byte[] content;
    private boolean file;

    public FileInfo(String relativePath, byte[] content, boolean file) {
        this.relativePath = relativePath;
        this.content = content;
        this.file = file;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public boolean isFile() {
        return file;
    }

    public void setFile(boolean file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileInfo [relativePath=" + relativePath + ", content=?]";
    }
}
