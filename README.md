# How to compile and run
Clone the repo and open `build.gradle` in Android Studio. Make sure to have an Android Emulator with an API version 8.0.x (Oreo) **or newer** set up. Also make sure you have an internet connection. After following these steps you will be able to run the app. To do so, simply press the "play-button" as shown below. You can also connect your own Android device if you wish to use this instead of an Android Emulator. To see how to connect your own device, se [this page](https://developer.android.com/studio/run/device). Remember that this is a multiplayer game, so in order to play atleast 2 players must participate in the game.

<img src="https://gitlab.stud.idi.ntnu.no/gruppe13tdt4240/viruswar/-/wikis/uploads/cf8c68f002deaf1c9cfc67bd5af1bf33/Skjermbilde_2022-04-22_kl._14.06.28.png"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" />

# Structure 
```
├── VirusWar.java
├── context
│   ├── BuildContext.java
│   └── Context.java
├── ecs
│   ├── OnlineEngine.java
│   ├── componenets
│   │   ├── ButtonComponent.java
│   │   ├── ConsumableComponent.java
│   │   ├── DeletedComponent.java
│   │   ├── DimensionComponent.java
│   │   ├── HiddenComponent.java
│   │   ├── IdentifierComponent.java
│   │   ├── LeadTextComponent.java
│   │   ├── LootComponent.java
│   │   ├── MapComponent.java
│   │   ├── OnlineComponent.java
│   │   ├── OnlinePathComponent.java
│   │   ├── PlayerComponent.java
│   │   ├── TextureRegionComponent.java
│   │   ├── TransformComponent.java
│   │   └── VelocityComponent.java
│   ├── factories
│   │   ├── ActorFactory.java
│   │   └── WorldFactory.java
│   ├── systems
│   │   ├── CameraSystem.java
│   │   ├── GameStateSystem.java
│   │   ├── LootSpawnSystem.java
│   │   ├── MapShrinkSystem.java
│   │   ├── OnlineConsumingSystem.java
│   │   ├── OnlineControlSystem.java
│   │   ├── OnlineDeleteSystem.java
│   │   ├── OnlineSendUserSystem.java
│   │   ├── OnlineSpawnSystem.java
│   │   ├── PlayerControlSystem.java
│   │   ├── PlayerMovementSystem.java
│   │   ├── QuitButtonSystem.java
│   │   ├── RenderingSystem.java
│   │   ├── ScoreSystem.java
│   │   ├── TextRenderSystem.java
│   │   └── UpdateSizeSystem.java
│   └── utils
│       ├── Camera.java
│       ├── EntityComparator.java
│       └── TouchController.java
├── screens
│   ├── CustomizeScreen.java
│   ├── EndScreen.java
│   ├── GameLobbyScreen.java
│   ├── GameScreen.java
│   ├── MainMenuScreen.java
│   ├── MenuBaseScreen.java
│   ├── PlayMenuScreen.java
│   ├── ScreenContext.java
│   ├── SettingsScreen.java
│   └── TutorialScreen.java
├── services
│   ├── assets
│   │   └── AssetManager.java
│   ├── backend
│   │   ├── BackendModel.java
│   │   └── BackendService.java
│   ├── lobby
│   │   ├── LobbyController.java
│   │   ├── LobbyModel.java
│   │   └── PinGenerator.java
│   ├── models
│   │   ├── BaseEntity.java
│   │   ├── Loot.java
│   │   └── Player.java
│   └── screen
│       ├── Screen.java
│       └── ScreenManager.java
└── utils
    └── Constants.java
```
