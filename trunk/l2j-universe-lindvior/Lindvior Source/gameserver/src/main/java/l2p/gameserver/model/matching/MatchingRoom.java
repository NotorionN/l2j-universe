package l2p.gameserver.model.matching;

import l2p.gameserver.instancemanager.MatchingRoomManager;
import l2p.gameserver.listener.actor.player.OnPlayerPartyInviteListener;
import l2p.gameserver.listener.actor.player.OnPlayerPartyLeaveListener;
import l2p.gameserver.model.Player;
import l2p.gameserver.model.PlayerGroup;
import l2p.gameserver.network.serverpackets.L2GameServerPacket;
import l2p.gameserver.network.serverpackets.SystemMessage2;
import l2p.gameserver.network.serverpackets.components.IStaticPacket;
import l2p.gameserver.network.serverpackets.components.SystemMsg;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author VISTALL
 * @date 18:38/11.06.2011
 */
public abstract class MatchingRoom implements PlayerGroup {
    private class PartyListenerImpl implements OnPlayerPartyInviteListener, OnPlayerPartyLeaveListener {
        @Override
        public void onPartyInvite(Player player) {
            broadcastPlayerUpdate(player);
        }

        @Override
        public void onPartyLeave(Player player) {
            broadcastPlayerUpdate(player);
        }
    }

    public static int PARTY_MATCHING = 0;
    public static int CC_MATCHING = 1;

    //
    public static int WAIT_PLAYER = 0;
    public static int ROOM_MASTER = 1;
    public static int PARTY_MEMBER = 2;
    public static int UNION_LEADER = 3;
    public static int UNION_PARTY = 4;
    public static int WAIT_PARTY = 5;
    public static int WAIT_NORMAL = 6;

    private final int _id;
    private int _minLevel;
    private int _maxLevel;
    private int _maxMemberSize;
    private int _lootType;
    private String _topic;

    private final PartyListenerImpl _listener = new PartyListenerImpl();
    protected final Player _leader;
    protected Set<Player> _members = new CopyOnWriteArraySet<Player>();

    public MatchingRoom(Player leader, int minLevel, int maxLevel, int maxMemberSize, int lootType, String topic) {
        _leader = leader;
        _id = MatchingRoomManager.getInstance().addMatchingRoom(this);
        _minLevel = minLevel;
        _maxLevel = maxLevel;
        _maxMemberSize = maxMemberSize;
        _lootType = lootType;
        _topic = topic;

        addMember0(leader, null);
    }

    //===============================================================================================================================================
    //                                                            Add/Remove Member
    //===============================================================================================================================================
    public boolean addMember(Player player) {
        if (_members.contains(player))
            return true;

        if (player.getLevel() < getMinLevel() || player.getLevel() > getMaxLevel() || getPlayers().size() >= getMaxMembersSize()) {
            player.sendPacket(notValidMessage());
            return false;
        }

        return addMember0(player, new SystemMessage2(enterMessage()).addName(player));
    }

    private boolean addMember0(Player player, L2GameServerPacket p) {
        if (!_members.isEmpty())
            player.addListener(_listener);

        _members.add(player);

        player.setMatchingRoom(this);

        for (Player $member : this)
            if ($member != player)
                $member.sendPacket(p, addMemberPacket($member, player));

        MatchingRoomManager.getInstance().removeFromWaitingList(player);
        player.sendPacket(infoRoomPacket(), membersPacket(player));
        player.sendChanges();
        return true;
    }

    public void removeMember(Player member, boolean oust) {
        if (!_members.remove(member))
            return;

        member.removeListener(_listener);
        member.setMatchingRoom(null);
        if (_members.isEmpty())
            disband();
        else {
            L2GameServerPacket infoPacket = infoRoomPacket();
            SystemMsg exitMessage0 = exitMessage(true, oust);
            L2GameServerPacket exitMessage = exitMessage0 != null ? new SystemMessage2(exitMessage0).addName(member) : null;
            for (Player player : this)
                player.sendPacket(infoPacket, removeMemberPacket(player, member), exitMessage);
        }

        member.sendPacket(closeRoomPacket(), exitMessage(false, oust));
        MatchingRoomManager.getInstance().addToWaitingList(member);
        member.sendChanges();
    }

    public void broadcastPlayerUpdate(Player player) {
        for (Player $member : MatchingRoom.this)
            $member.sendPacket(updateMemberPacket($member, player));
    }

    public void disband() {
        for (Player player : this) {
            player.removeListener(_listener);
            player.sendPacket(closeRoomMessage());
            player.sendPacket(closeRoomPacket());
            player.setMatchingRoom(null);
            player.sendChanges();

            MatchingRoomManager.getInstance().addToWaitingList(player);
        }

        _members.clear();

        MatchingRoomManager.getInstance().removeMatchingRoom(this);
    }

    //===============================================================================================================================================
    //                                                            Abstracts
    //===============================================================================================================================================
    public abstract SystemMsg notValidMessage();

    public abstract SystemMsg enterMessage();

    public abstract SystemMsg exitMessage(boolean toOthers, boolean kick);

    public abstract SystemMsg closeRoomMessage();

    public abstract L2GameServerPacket closeRoomPacket();

    public abstract L2GameServerPacket infoRoomPacket();

    public abstract L2GameServerPacket addMemberPacket(Player $member, Player active);

    public abstract L2GameServerPacket removeMemberPacket(Player $member, Player active);

    public abstract L2GameServerPacket updateMemberPacket(Player $member, Player active);

    public abstract L2GameServerPacket membersPacket(Player active);

    public abstract int getType();

    public abstract int getMemberType(Player member);

    //===============================================================================================================================================
    //                                                            Broadcast
    //===============================================================================================================================================
    @Override
    public void broadCast(IStaticPacket... arg) {
        for (Player player : this)
            player.sendPacket(arg);
    }

    //===============================================================================================================================================
    //                                                            Getters
    //===============================================================================================================================================
    public int getId() {
        return _id;
    }

    public int getMinLevel() {
        return _minLevel;
    }

    public int getMaxLevel() {
        return _maxLevel;
    }

    public String getTopic() {
        return _topic;
    }

    public int getMaxMembersSize() {
        return _maxMemberSize;
    }

    public int getLocationId() {
        return MatchingRoomManager.getInstance().getLocation(_leader);
    }

    public Player getLeader() {
        return _leader;
    }

    public Collection<Player> getPlayers() {
        return _members;
    }

    public int getLootType() {
        return _lootType;
    }

    @Override
    public Iterator<Player> iterator() {
        return _members.iterator();
    }

    //===============================================================================================================================================
    //                                                            Setters
    //===============================================================================================================================================
    public void setMinLevel(int minLevel) {
        _minLevel = minLevel;
    }

    public void setMaxLevel(int maxLevel) {
        _maxLevel = maxLevel;
    }

    public void setTopic(String topic) {
        _topic = topic;
    }

    public void setMaxMemberSize(int maxMemberSize) {
        _maxMemberSize = maxMemberSize;
    }

    public void setLootType(int lootType) {
        _lootType = lootType;
    }
}