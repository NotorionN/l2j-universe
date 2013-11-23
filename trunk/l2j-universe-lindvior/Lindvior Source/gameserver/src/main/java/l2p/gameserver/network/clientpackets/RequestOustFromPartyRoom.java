package l2p.gameserver.network.clientpackets;

import l2p.gameserver.model.GameObjectsStorage;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.matching.MatchingRoom;

/**
 * format (ch) d
 */
public class RequestOustFromPartyRoom extends L2GameClientPacket {
    private int _objectId;

    @Override
    protected void readImpl() {
        _objectId = readD();
    }

    @Override
    protected void runImpl() {
        Player player = getClient().getActiveChar();

        MatchingRoom room = player.getMatchingRoom();
        if (room == null || room.getType() != MatchingRoom.PARTY_MATCHING)
            return;

        if (room.getLeader() != player)
            return;

        Player member = GameObjectsStorage.getPlayer(_objectId);
        if (member == null)
            return;

        if (member == room.getLeader())
            return;

        room.removeMember(member, true);
    }
}