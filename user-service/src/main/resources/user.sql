#namespace("role")
  #sql("findByUserId")
    SELECT sr.* FROM  sys_role sr JOIN sys_user_role sur ON sr.id = sur.role_id
    WHERE sur.user_id = #p(userId)
  #end
#end

#namespace("menu")
  #sql("findByRoleId")
    SELECT sm.* FROM  sys_menu sm JOIN sys_role_menu srm ON sm.id = srm.menu_id
    WHERE sur.role_id = #p(roleId)
    ORDER BY sm.sort,sm.id
  #end
#end