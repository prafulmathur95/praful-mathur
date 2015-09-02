.class Lcom/google/appinventor/components/runtime/ListPickerActivity$1;
.super Ljava/lang/Object;
.source "ListPickerActivity.java"

# interfaces
.implements Landroid/text/TextWatcher;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/appinventor/components/runtime/ListPickerActivity;->onCreate(Landroid/os/Bundle;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/appinventor/components/runtime/ListPickerActivity;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/ListPickerActivity;)V
    .locals 0
    .parameter

    .prologue
    .line 105
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/ListPickerActivity$1;->this$0:Lcom/google/appinventor/components/runtime/ListPickerActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public afterTextChanged(Landroid/text/Editable;)V
    .locals 0
    .parameter "arg0"

    .prologue
    .line 121
    return-void
.end method

.method public beforeTextChanged(Ljava/lang/CharSequence;III)V
    .locals 0
    .parameter "arg0"
    .parameter "arg1"
    .parameter "arg2"
    .parameter "arg3"

    .prologue
    .line 116
    return-void
.end method

.method public onTextChanged(Ljava/lang/CharSequence;III)V
    .locals 1
    .parameter "cs"
    .parameter "arg1"
    .parameter "arg2"
    .parameter "arg3"

    .prologue
    .line 110
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/ListPickerActivity$1;->this$0:Lcom/google/appinventor/components/runtime/ListPickerActivity;

    iget-object v0, v0, Lcom/google/appinventor/components/runtime/ListPickerActivity;->adapter:Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;

    invoke-virtual {v0}, Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;->getFilter()Landroid/widget/Filter;

    move-result-object v0

    invoke-virtual {v0, p1}, Landroid/widget/Filter;->filter(Ljava/lang/CharSequence;)V

    .line 111
    return-void
.end method
