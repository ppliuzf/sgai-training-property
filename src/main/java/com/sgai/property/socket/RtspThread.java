package com.sgai.property.socket;

import com.sgai.property.video.service.RtspProcessor;

/**
 * 描述:
 *
 * @author ppliu
 * created in 2019/5/5 9:19
 */
public class RtspThread extends Thread {
    private RtspProcessor rtspProcessor;
    private boolean flag = true;
    public RtspThread(RtspProcessor rtspProcessor) {
        this.rtspProcessor = rtspProcessor;
    }
    @Override
    public void run() {
        while (flag) {
            try {
                rtspProcessor.sendBase64();
            }catch (Exception e){
               break;
            }
        }
    }

    @Override
    public void interrupt() {
        this.flag = false;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
