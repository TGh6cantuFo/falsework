#namespace("role")
  #sql("findByUserId")
    SELECT sr.* FROM  sys_role sr JOIN sys_user_role sur ON sr.id = sur.role_id
    WHERE sur.user_id = #p(0)
  #end
#end

#namespace("menu")
  #sql("findByRoleId")
    SELECT sm.* FROM  sys_menu sm JOIN sys_role_menu srm ON sm.id = srm.menu_id
    WHERE srm.role_id = #p(0)
    ORDER BY sm.sort,sm.id
  #end
#end