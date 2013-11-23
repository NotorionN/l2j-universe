package ai.Heine.FieldOfSelenceAndWhispers;

import l2p.gameserver.ai.CharacterAI;
import l2p.gameserver.ai.CtrlEvent;
import l2p.gameserver.model.Creature;
import l2p.gameserver.model.instances.NpcInstance;
import l2p.gameserver.network.serverpackets.components.ChatType;
import l2p.gameserver.network.serverpackets.components.NpcString;
import l2p.gameserver.scripts.Functions;

import java.util.List;

/**
 * - User: Mpa3uHaKaMa3e
 * - Date: 26.06.12
 * - Time: 16:51
 * - AI для нпц Brazier Of Purity (18806).
 * - Если был атакован то кричит в чат и зовут на помощь.
 */
public class BrazierOfPurity extends CharacterAI {
    private boolean _firstTimeAttacked = true;

    public BrazierOfPurity(Creature actor) {
        super(actor);
    }

    @Override
    protected void onEvtAttacked(Creature attacker, int damage) {
        NpcInstance actor = (NpcInstance) getActor();
        if (actor == null || actor.isDead())
            return;

        if (_firstTimeAttacked) {
            _firstTimeAttacked = false;
            Functions.npcSay(actor, NpcString.THE_PURIFICATION_FIELD_IS_BEING_ATTACKED_GUARDIAN_SPIRITS_PROTECT_THE_MAGIC_FORCE, ChatType.ALL, 15000);
            List<NpcInstance> around = actor.getAroundNpc(1500, 300);
            if (around != null && !around.isEmpty()) {
                for (NpcInstance npc : around) {
                    if (npc.isMonster() && npc.getNpcId() == 22658 || npc.getNpcId() == 22659)
                        npc.getAI().notifyEvent(CtrlEvent.EVT_AGGRESSION, attacker, 5000);
                }
            }
        }
        super.onEvtAttacked(attacker, damage);
    }

    @Override
    protected void onEvtDead(Creature killer) {
        _firstTimeAttacked = true;
        super.onEvtDead(killer);
    }
}