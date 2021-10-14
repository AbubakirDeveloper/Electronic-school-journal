interface Plan{
    fun fileRead(groupName:String)
    fun fileWrite(groupName:String)
    fun fileWriteGroupNames()

    //service or settings mode
    fun addMan()
    fun removeMan()
    fun addGroup()

    //teacher mode
    fun groupActions()
    fun list()
}