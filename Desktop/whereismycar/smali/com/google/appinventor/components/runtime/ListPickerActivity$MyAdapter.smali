.class Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;
.super Landroid/widget/ArrayAdapter;
.source "ListPickerActivity.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/appinventor/components/runtime/ListPickerActivity;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "MyAdapter"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/widget/ArrayAdapter",
        "<",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field private final mContext:Landroid/content/Context;


# direct methods
.method public constructor <init>(Landroid/content/Context;[Ljava/lang/String;)V
    .locals 1
    .parameter "context"
    .parameter "items"

    .prologue
    .line 172
    const/high16 v0, 0x109

    invoke-direct {p0, p1, v0, p2}, Landroid/widget/ArrayAdapter;-><init>(Landroid/content/Context;I[Ljava/lang/Object;)V

    .line 173
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;->mContext:Landroid/content/Context;

    .line 174
    return-void
.end method


# virtual methods
.method public getItemId(I)J
    .locals 2
    .parameter "position"

    .prologue
    .line 178
    invoke-virtual {p0, p1}, Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v0

    int-to-long v0, v0

    return-wide v0
.end method

.method public getView(ILandroid/view/View;Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 4
    .parameter "position"
    .parameter "convertView"
    .parameter "parent"

    .prologue
    .line 183
    move-object v0, p2

    check-cast v0, Landroid/widget/TextView;

    .line 184
    .local v0, tv:Landroid/widget/TextView;
    if-nez v0, :cond_0

    .line 185
    iget-object v1, p0, Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;->mContext:Landroid/content/Context;

    invoke-static {v1}, Landroid/view/LayoutInflater;->from(Landroid/content/Context;)Landroid/view/LayoutInflater;

    move-result-object v1

    const v2, 0x1090003

    const/4 v3, 0x0

    invoke-virtual {v1, v2, p3, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;Z)Landroid/view/View;

    move-result-object v0

    .end local v0           #tv:Landroid/widget/TextView;
    check-cast v0, Landroid/widget/TextView;

    .line 187
    .restart local v0       #tv:Landroid/widget/TextView;
    :cond_0
    invoke-virtual {p0, p1}, Lcom/google/appinventor/components/runtime/ListPickerActivity$MyAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/CharSequence;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 188
    sget v1, Lcom/google/appinventor/components/runtime/ListPickerActivity;->itemColor:I

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setTextColor(I)V

    .line 189
    return-object v0
.end method
