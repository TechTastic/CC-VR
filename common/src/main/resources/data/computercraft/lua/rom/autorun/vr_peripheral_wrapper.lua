--- This peripheral is added by CC: VR and allows CC: Tweaked computers to access a bound player's VR controllers and headset via Vivecraft!
--
-- This addon also includes [CC: Advanced Math][advanced-math] which provides the `quaternion` API used for the rotation in body part data.
--
-- [advanced-math]: https://techtastic.github.io/Advanced-Math/
--
-- @module vr
-- @usage
-- -- assuming the peripheral block is to the left of the computer
-- local vr = peripheral.wrap("left")

--- Determines whether a player has been bound ot the peripheral.
-- @function hasBoundPlayer
-- @treturn boolean whether a player has been bound
-- @usage
-- if vr.hasBoundPlayer() then
--     print("Player is Bound!")
-- end

--- Determines whether the bound player is in VR mode.
-- @function isInVR
-- @treturn boolean whether the bound player is in VR mode
-- @raise This method errors if there is no bound player.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     print("Player is Bound and in VR!")
-- end

--- Determines whether the bound player in VR has set themselves as left-handed.
-- @function isLeftHanded
-- @treturn boolean whether the bound player in VR is left-handed
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() and vr.isLeftHanded() then
--     print("Player is Bound, in VR, and left-handed!")
-- end

--- Determines whether the bound player in VR is seated.
-- @function isSeated
-- @treturn boolean whether the bound player in VR is seated
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() and vr.isSeated() then
--     print("Player is Bound, in VR, and seated!")
-- end

--- Determines whether the bound player in VR is crawling.
-- @function isLeftHanded
-- @treturn boolean whether the bound player in VR is crawling
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() and vr.isCrawling() then
--     print("Player is Bound, in VR, and crawling!")
-- end

--- Gets the height scale of the bound player in VR mode.
-- @function getHeightScale
-- @treturn number the height scale of the bound VR player
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     print("Height Scale: " .. tostring(vr.getHeightScale()))
-- end

--- Gets the world scale of the bound player in VR mode.
-- @function getWorldScale
-- @treturn number the world scale of the bound VR player
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     print("World Scale: " .. tostring(vr.getWorldScale()))
-- end

--- Gets what Full Body Tracking mode the bound player is in.
-- @function getFBTMode
-- @treturn string|nil the FBT mode enum value or nil if the pose is not loaded
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     print("Full Body Tracking: " .. vr.getFBTMode())
-- end

--- Triggers a haptic pulse for the bound player on the targeted body part
-- @function triggerHapticPulse
-- @tparam string bodyPart the body part enum value
-- @tparam number duration the duration of the pulse in seconds
-- @tparam number[opt=160] frequency the frequency of the pulse
-- @tparam number[opt=1] amplitude the amplitude of the pulse
-- @tparam number[opt=0] delay the delay of the pulse in seconds
-- @raise This method errors if there is no bound player or if the provided body part enum string is invalid.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     br.triggerHapticPulse("MAIN_HAND", 1)
-- end

--- Gets the positioning and orientation data of the headset.
-- @function getHead
-- @treturn table|nil the data of the headset (`pos`, `dir`, `rotation`, `pitch`, `roll`, and `yaw` in a table) or nil if the pose is not loaded
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     local data = vr.getHead()
--     if data ~= nil then
--         print("Position: " .. tostring(data.pos)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Direction: " .. tostring(data.dir)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Rotation: " .. tostring(data.rotation)) -- this is a CC: Advanced Math quaternion (1 + 0i + 0j + 0k)
--         print("Pitch: " .. tostring(data.pitch))
--         print("Roll: " .. tostring(data.roll))
--         print("Yaw: " .. tostring(data.yaw))
--     end
-- end

--- Gets the positioning and orientation data of the main hand controller.
-- @function getMainHand
-- @treturn table|nil the data of the main hand controller (`pos`, `dir`, `rotation`, `pitch`, `roll`, and `yaw` in a table) or nil if the pose is not loaded
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     local data = vr.getMainHand()
--     if data ~= nil then
--         print("Position: " .. tostring(data.pos)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Direction: " .. tostring(data.dir)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Rotation: " .. tostring(data.rotation)) -- this is a CC: Advanced Math quaternion (1 + 0i + 0j + 0k)
--         print("Pitch: " .. tostring(data.pitch))
--         print("Roll: " .. tostring(data.roll))
--         print("Yaw: " .. tostring(data.yaw))
--     end
-- end

--- Gets the positioning and orientation data of the off-hand controller.
-- @function getOffhand
-- @treturn table|nil the data of the off-hand controller (`pos`, `dir`, `rotation`, `pitch`, `roll`, and `yaw` in a table) or nil if the pose is not loaded
-- @raise This method errors if there is no bound player or if the bound player is not in VR mode.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     local data = vr.getOffhand()
--     if data ~= nil then
--         print("Position: " .. tostring(data.pos)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Direction: " .. tostring(data.dir)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Rotation: " .. tostring(data.rotation)) -- this is a CC: Advanced Math quaternion (1 + 0i + 0j + 0k)
--         print("Pitch: " .. tostring(data.pitch))
--         print("Roll: " .. tostring(data.roll))
--         print("Yaw: " .. tostring(data.yaw))
--     end
-- end

--- Gets the positioning and orientation data of the body part.
-- @function getBodyPartData
-- @tparam string bodyPart the body part enum value
-- @treturn table|nil the data of the body part (`pos`, `dir`, `rotation`, `pitch`, `roll`, and `yaw` in a table) or nil if the pose is not loaded
-- @raise This method errors if there is no bound player, if the bound player is not in VR mode, or if the provided body part enum string is invalid.
-- @usage
-- if vr.hasBoundPlayer() and vr.isInVR() then
--     local data = vr.getBodyPartData("MAIN_HAND")
--     if data ~= nil then
--         print("Position: " .. tostring(data.pos)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Direction: " .. tostring(data.dir)) -- this is a CC: Tweaked vector (0,0,0)
--         print("Rotation: " .. tostring(data.rotation)) -- this is a CC: Advanced Math quaternion (1 + 0i + 0j + 0k)
--         print("Pitch: " .. tostring(data.pitch))
--         print("Roll: " .. tostring(data.roll))
--         print("Yaw: " .. tostring(data.yaw))
--     end
-- end

local native = peripheral.call

peripheral.call = function(...)
	local args = table.pack(...)
	local name = args[1]
	local method = args[2]
	if peripheral.hasType(name, "vr") then
		if method == "getHead" or method == "getMainHand" or method == "getOffhand" or method == "getBodyPartData" then
			local status, result = pcall(function() return native(table.unpack(args)) end)
            if not status then
                error(result)
            end
			if result == nil then
				return nil
			end
			result.pos = vector.new(result.pos.x, result.pos.y, result.pos.z)
			result.dir = vector.new(result.dir.x, result.dir.y, result.dir.z)
			result.rotation = quaternion.fromComponents(result.rotation.x, result.rotation.y, result.rotation.z, result.rotation.w)
			return result
		end
	end
	return native(table.unpack(args))
end