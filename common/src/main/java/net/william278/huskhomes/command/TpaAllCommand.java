/*
 * This file is part of HuskHomes, licensed under the Apache License 2.0.
 *
 *  Copyright (c) William278 <will27528@gmail.com>
 *  Copyright (c) contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package net.william278.huskhomes.command;

import net.william278.huskhomes.HuskHomes;
import net.william278.huskhomes.user.OnlineUser;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TpaAllCommand extends InGameCommand {

    protected TpaAllCommand(@NotNull HuskHomes plugin) {
        super(List.of("tpaall"), "", plugin);
        setOperatorCommand(true);
    }

    @Override
    public void execute(@NotNull OnlineUser executor, @NotNull String[] args) {
        if (plugin.getUserList().size() <= 1) {
            plugin.getLocales().getLocale("error_no_players_online")
                    .ifPresent(executor::sendMessage);
            return;
        }

        if (plugin.getManager().requests().isIgnoringRequests(executor)) {
            plugin.getLocales().getLocale("error_ignoring_teleport_requests")
                    .ifPresent(executor::sendMessage);
            return;
        }

        plugin.getManager().requests().sendTeleportAllRequest(executor);
        plugin.getLocales().getLocale("tpaall_request_sent")
                .ifPresent(executor::sendMessage);
    }

}
