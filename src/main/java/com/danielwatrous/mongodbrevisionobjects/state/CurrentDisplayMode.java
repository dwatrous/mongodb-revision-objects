/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.danielwatrous.mongodbrevisionobjects.state;

import com.danielwatrous.mongodbrevisionobjects.model.DisplayMode;

/**
 *
 * @author watrous
 */
public class CurrentDisplayMode implements DisplayMode {
    private boolean previewModeActive = false;

    public boolean isPreviewModeActive() {
        return previewModeActive;
    }

    public void enablePreviewModeActive() {
        this.previewModeActive = true;
    }

    public void disablePreviewModeActive() {
        this.previewModeActive = false;
    }

}
