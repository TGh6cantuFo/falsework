#define findList(table,condition)
  select * from #(table)
  #for(x : condition)
    #(for.index == 0 ? " where " : " and " ) #(x.key) #p(x.value)
  #end
#end

#sql("find")
  #@findList(table,condition)
#end