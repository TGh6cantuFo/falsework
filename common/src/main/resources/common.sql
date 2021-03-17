#define getCondition(condition)
  #for(x : condition)
    #(for.index == 0 ? " WHERE " : " AND " ) #(x.key) #p(x.value)
  #end
#end

#sql("find")
  SELECT * FROM #(table)
  #@getCondition(condition)
#end

#sql("paginate-except")
  FROM #(table)
  #@getCondition(condition)
#end

#sql("paginate-select")
  SELECT *
#end