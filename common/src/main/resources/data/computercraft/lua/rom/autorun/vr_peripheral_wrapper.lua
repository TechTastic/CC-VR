local native = peripheral.call

peripheral.call = function(...)
	local args = table.pack(...)
	local name = args[1]
	local method = args[2]
	if peripheral.hasType(name, "vr") then
		if method == "getHead" or method == "getMainHand" or method == "getOffhand" or method == "getBodyPartData" then
			local result, err = pcall(function() return native(table.unpack(args)) end)
            if err then
                error(err)
            end
			if result == nil then
				return result
			end
			result.pos = vector.new(result.pos.x, result.pos.y, result.pos.z)
			result.dir = vector.new(result.dir.x, result.dir.y, result.dir.z)
			result.rotation = quaternion.fromComponents(result.rotation.x, result.rotation.y, result.rotation.z, result.rotation.w)
			return result
		end
	end
	return native(table.unpack(args))
end