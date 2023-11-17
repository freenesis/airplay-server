package org.freenesis.airplay.jap2server;

import org.freenesis.airplay.jap2lib.rtsp.AudioStreamInfo;
import org.freenesis.airplay.jap2lib.rtsp.VideoStreamInfo;

public interface AirplayDataConsumer {

    void onVideo(byte[] video);

    void onVideoFormat(VideoStreamInfo videoStreamInfo);

    void onAudio(byte[] audio);

    void onAudioFormat(AudioStreamInfo audioInfo);
}
