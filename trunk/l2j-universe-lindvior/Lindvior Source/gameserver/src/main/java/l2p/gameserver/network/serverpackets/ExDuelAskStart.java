/*
 * Copyright Mazaffaka Project (c) 2013.
 */

package l2p.gameserver.network.serverpackets;

public class ExDuelAskStart extends L2GameServerPacket {
    String _requestor;
    int _isPartyDuel;

    public ExDuelAskStart(String requestor, int isPartyDuel) {
        _requestor = requestor;
        _isPartyDuel = isPartyDuel;
    }

    @Override
    protected final void writeImpl() {
        writeEx(0x4D);
        writeS(_requestor);
        writeD(_isPartyDuel);
    }
}