Resource Control
================

This mod is designed for server owners and modpack makers. It allows the controlling of a users resource packs through commands.


This mod can:
--------------

*   Turn on/off a pack
*   Rearrange the pack order
*   Reload resources on each command, or after multiple have been run

Command usage:
--------------

All commands only affect the player running them, but since they don't require admin `/execute as [player] run rc <command>` can work for admins/datapacks to control other players.

---

`/rc reloadall`

Reloads all resources
  
---

The following commands end with an optional `[reload]` parameter.
If not included, will be true. Controls whether the players resources should be reloaded after the command is run.
Alternatively, the changes can take effect next time `reloadall` is run, or F3+T is used.

---

`/rc activate [packname] [reload]`

Activates a resource pack with the filename of `[packname]`.
Zip files will need the `.zip` specified. 

Examples:

`/rc activate grass.zip`
Activates the resource pack named "grass.zip" (and reloads)

`/rc activate "Xray ultimate" false`
Activates the resource pack (folder) named "Xray ultimate" (and doesn't reload yet)

---

`/rc deactivate [packname] [reload]`

Same as `activate` but de-activates

---

`/rc moveup [packname] [amount] [reload]`

Moves the activated pack with `[packname]` up the `[amount]` in the priority list. 
Set amount to 0 (or don't specify amount) to go all the way to the top.

---

`/rc movedown [packname] [amount] [reload]`

Same as `moveup` but moves it down in the priority.
Set amount to 0 (or don't specify amount) to go all the way to the bottom.

---

`/rc logpacks`

Logs all resource packs found (of the player its run as) to the server console.
(Or the hosts log if on a LAN server)