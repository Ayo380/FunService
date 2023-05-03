# FunService

FunService is an Android app designed to store pictures and audio clips and expose a service for use by other Android apps. The app includes at least three pictures and three audio clips of variable duration, and the service supports two types of client requests: picture retrieval and audio playback.
The service is implemented in a thread-safe manner using AIDL, and audio playback is handled by Android's MediaPlayerService. FunClient, another app in the project, binds to the FunCenter service to enable picture requests and audio playback controls.
