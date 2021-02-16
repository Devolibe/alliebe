package com.alliebe.mastersejin.dtos

data class StoryParentDTO(
    var question : String? = null,
    var children_comment : List<StoryChildCommentDTO>? = null,
    var children_album : List<StoryChildAlbumDTO>? = null
)
