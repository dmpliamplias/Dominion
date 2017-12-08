package com.weddingcrashers.servermodels;

import java.io.Serializable;

/**
 *  @author Michel Schlatter
 *  */
public enum Methods implements Serializable {
    Login,
    Login_SetUser_TestPurposesOnly,
    Rankings,
    SetViewStatus,
    Lobby_Players,
    Chat,
    Register,
    Connect,
    StartGame,
    InitialCardSets,
    CardPlayed,
    UpdateRound,
    TurnFinished,
    BuyCard,
    Client_Server_Error,
}
