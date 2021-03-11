#define getCondition(condition)
  #for(x : condition)
    #(for.index == 0 ? " where " : " and " ) #(x.key) #p(x.value)
  #end
#end