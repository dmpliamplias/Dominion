package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public enum Methods implements Serializable {
    // TODO: 29.09.2017 mschlatte: enums uppercase for right javadoc click **/ then enter
    Login,
    Login_SetUser_TestPurposesOnly,
    ShuffleCards,
    Rankings,
    SetViewStatus,
    Lobby_Players,
    Chat,
    Register,
    Connect,
    StartGame,
    SpreadCards,
    CardPlayed,
    UpdateRound,
    TurnFinished,
    BuyCard,
    Client_Server_Error,
}
