  local key = KEYS[1]
  local counter = redis.call("GET", key)

  local counter_as_table = {}
  counter:gsub(".", function(c) table.insert(counter_as_table, c) end)

  local incr_next = #counter_as_table

  local carrying = true
  while counter_as_table[incr_next] and carrying do
    local incremented_digit = counter_as_table[incr_next] + 1

    if incremented_digit < 10
    then carrying = false
    else incremented_digit = 0
    end

    counter_as_table[incr_next] = incremented_digit

    incr_next = incr_next - 1
  end

  if carrying then
    table.insert(counter_as_table, 1, "1")
  end

  local output = ""

  for i, v in pairs(counter_as_table) do
    output = output .. v
  end

  redis.call("SET", key, output)
  return output