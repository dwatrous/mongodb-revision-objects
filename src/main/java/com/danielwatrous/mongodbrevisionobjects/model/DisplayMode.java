/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.model;

/**
 *
 * @author watrous
 */
public interface DisplayMode {
    boolean isPreviewModeActive();
    void enablePreviewModeActive();
    void disablePreviewModeActive();
}
