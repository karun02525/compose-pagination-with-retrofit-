package com.example.paginationcompose.model



data class ResponseNotificationModel(
    val code: Int,
    val `data`: Data,
    val error: Any,
    val message: String,
)

data class Data(
    val notifications: List<NotificationList>,
    val page:Int
)

data class NotificationList(
    val __v: Int,
    val _id: String,
    val additionalInfo: AdditionalInfo,
    val created_at: String,
    val notifyType: Int,
    val postId: String,
    val postImage: String,
    val receiverId: String,
    val receiverMessage: ReceiverMessage,
    val receiverName: String,
    val senderId: String,
    val senderMessage: String,
    val senderName: String,
    val senderProPic: String?,
    val status: Int,
){
    fun hasSenderProfilePicture(): Boolean {


       var senderProfilePicture: String = if (!senderProPic.isNullOrEmpty()) {
            senderProPic
        } else {
            ""
        }

     return  !senderProfilePicture.endsWith(NotificationFemaleAvatarExtension)
                && !senderProfilePicture.endsWith(NotificationDefaultAvatarExtension)
                && !senderProfilePicture.endsWith(NotificationMaleAvatarExtension)
                && !senderProfilePicture.contains("null")
                && senderProfilePicture.isNotEmpty()
    }

    private companion object {
        private const val NotificationDefaultAvatarExtension = "defaultUserPic.png"
        private const val NotificationFemaleAvatarExtension = "female.png"
        private const val NotificationMaleAvatarExtension = "male.png"
    }
}

data class AdditionalInfo(
    val badgeType: String,
    val message: String,
    val senderID: String,
    val status: String,
    val type: String,
    val userId: String,
    val verificationID: String
)

data class ReceiverMessage(
    val bengali: String,
    val english: String,
    val gujarati: String,
    val hindi: String,
    val kannada: String,
    val malayalam: String,
    val marathi: String,
    val odia: String,
    val odiya: String,
    val punjabi: String,
    val tamil: String,
    val telugu: String
)

