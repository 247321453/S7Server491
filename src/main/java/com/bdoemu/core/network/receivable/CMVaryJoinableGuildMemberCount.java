// 
// Decompiled by Procyon v0.5.30
// 

package com.bdoemu.core.network.receivable;

import com.bdoemu.commons.network.ReceivablePacket;
import com.bdoemu.core.network.GameClient;
import com.bdoemu.gameserver.model.creature.player.Player;
import com.bdoemu.gameserver.model.team.guild.Guild;
import com.bdoemu.gameserver.model.team.guild.events.VaryJoinableGuildMemberCountEvent;

public class CMVaryJoinableGuildMemberCount extends ReceivablePacket<GameClient> {
    private long guildId;
    private long unk;

    public CMVaryJoinableGuildMemberCount(final short opcode) {
        super(opcode);
    }

    protected void read() {
        this.guildId = this.readQ();
        this.unk = this.readQ();
    }

    public void runImpl() {
        final Player player = ((GameClient) this.getClient()).getPlayer();
        if (player != null) {
            final Guild guild = player.getGuild();
            if (guild != null) {
                guild.onEvent(new VaryJoinableGuildMemberCountEvent(player, guild));
            }
        }
    }
}
