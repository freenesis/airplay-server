# airplay-server

[![build](https://github.com/freenesis/airplay-server/actions/workflows/build.yaml/badge.svg)](https://github.com/freenesis/airplay-server/actions/workflows/build.yaml)


This is example of [airplay-lib](https://github.com/freenesis/airplay-lib) usage.

It's under development.

## How to use?

* Add java-airplay-server [dependency](https://jitpack.io/#serezhka/java-airplay-server) to your project

* Implement AirplayDataConsumer and start AirPlayServer, for example:
```java
FileChannel videoFileChannel = FileChannel.open(Paths.get("video.h264"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
FileChannel audioFileChannel = FileChannel.open(Paths.get("audio.pcm"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

AirplayDataConsumer dumper = new AirplayDataConsumer() {
    
    @Override
    public void onVideo(byte[] video) {
        videoFileChannel.write(ByteBuffer.wrap(video));
    }

    
    @Override
    public void onAudio(byte[] audio) {
        if (FdkAacLib.isInitialized()) {
            byte[] audioDecoded = new byte[480 * 4];
            FdkAacLib.decodeFrame(audio, audioDecoded);
            audioFileChannel.write(ByteBuffer.wrap(audioDecoded));
        }
    }
};

String serverName = "AirPlayServer";
int airPlayPort = 15614;
int airTunesPort = 5001;
new AirPlayServer(serverName, airPlayPort, airTunesPort, dumper).start();
```