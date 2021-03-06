.class public Lkawa/standard/require;
.super Lkawa/lang/Syntax;
.source "require.java"


# static fields
.field private static final SLIB_PREFIX:Ljava/lang/String; = "gnu.kawa.slib."

.field static featureMap:Ljava/util/Hashtable;

.field public static final require:Lkawa/standard/require;


# direct methods
.method static constructor <clinit>()V
    .locals 2

    .prologue
    .line 18
    new-instance v0, Lkawa/standard/require;

    invoke-direct {v0}, Lkawa/standard/require;-><init>()V

    sput-object v0, Lkawa/standard/require;->require:Lkawa/standard/require;

    .line 19
    sget-object v0, Lkawa/standard/require;->require:Lkawa/standard/require;

    const-string v1, "require"

    invoke-virtual {v0, v1}, Lkawa/standard/require;->setName(Ljava/lang/String;)V

    .line 59
    new-instance v0, Ljava/util/Hashtable;

    invoke-direct {v0}, Ljava/util/Hashtable;-><init>()V

    sput-object v0, Lkawa/standard/require;->featureMap:Ljava/util/Hashtable;

    .line 70
    const-string v0, "generic-write"

    const-string v1, "gnu.kawa.slib.genwrite"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 71
    const-string v0, "pretty-print"

    const-string v1, "gnu.kawa.slib.pp"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 72
    const-string v0, "pprint-file"

    const-string v1, "gnu.kawa.slib.ppfile"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 73
    const-string v0, "printf"

    const-string v1, "gnu.kawa.slib.printf"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 74
    const-string v0, "xml"

    const-string v1, "gnu.kawa.slib.XML"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 75
    const-string v0, "readtable"

    const-string v1, "gnu.kawa.slib.readtable"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 76
    const-string v0, "srfi-10"

    const-string v1, "gnu.kawa.slib.readtable"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 77
    const-string v0, "http"

    const-string v1, "gnu.kawa.servlet.HTTP"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 78
    const-string v0, "servlets"

    const-string v1, "gnu.kawa.servlet.servlets"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 79
    const-string v0, "srfi-1"

    const-string v1, "gnu.kawa.slib.srfi1"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 80
    const-string v0, "list-lib"

    const-string v1, "gnu.kawa.slib.srfi1"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 81
    const-string v0, "srfi-2"

    const-string v1, "gnu.kawa.slib.srfi2"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 82
    const-string v0, "and-let*"

    const-string v1, "gnu.kawa.slib.srfi2"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 83
    const-string v0, "srfi-13"

    const-string v1, "gnu.kawa.slib.srfi13"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 84
    const-string v0, "string-lib"

    const-string v1, "gnu.kawa.slib.srfi13"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 85
    const-string v0, "srfi-34"

    const-string v1, "gnu.kawa.slib.srfi34"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 86
    const-string v0, "srfi-35"

    const-string v1, "gnu.kawa.slib.conditions"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 87
    const-string v0, "condition"

    const-string v1, "gnu.kawa.slib.conditions"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 88
    const-string v0, "conditions"

    const-string v1, "gnu.kawa.slib.conditions"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 89
    const-string v0, "srfi-37"

    const-string v1, "gnu.kawa.slib.srfi37"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 90
    const-string v0, "args-fold"

    const-string v1, "gnu.kawa.slib.srfi37"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 91
    const-string v0, "srfi-64"

    const-string v1, "gnu.kawa.slib.testing"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 92
    const-string v0, "testing"

    const-string v1, "gnu.kawa.slib.testing"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 93
    const-string v0, "srfi-69"

    const-string v1, "gnu.kawa.slib.srfi69"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 94
    const-string v0, "hash-table"

    const-string v1, "gnu.kawa.slib.srfi69"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 95
    const-string v0, "basic-hash-tables"

    const-string v1, "gnu.kawa.slib.srfi69"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 96
    const-string v0, "srfi-95"

    const-string v1, "kawa.lib.srfi95"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 97
    const-string v0, "sorting-and-merging"

    const-string v1, "kawa.lib.srfi95"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 98
    const-string v0, "regex"

    const-string v1, "kawa.lib.kawa.regex"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 99
    const-string v0, "pregexp"

    const-string v1, "gnu.kawa.slib.pregexp"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 100
    const-string v0, "gui"

    const-string v1, "gnu.kawa.slib.gui"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 101
    const-string v0, "swing-gui"

    const-string v1, "gnu.kawa.slib.swing"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 102
    const-string v0, "android-defs"

    const-string v1, "gnu.kawa.android.defs"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 103
    const-string v0, "syntax-utils"

    const-string v1, "gnu.kawa.slib.syntaxutils"

    invoke-static {v0, v1}, Lkawa/standard/require;->map(Ljava/lang/String;Ljava/lang/String;)V

    .line 104
    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 16
    invoke-direct {p0}, Lkawa/lang/Syntax;-><init>()V

    return-void
.end method

.method public static find(Ljava/lang/String;)Ljava/lang/Object;
    .locals 1
    .parameter "typeName"

    .prologue
    .line 113
    invoke-static {}, Lgnu/expr/ModuleManager;->getInstance()Lgnu/expr/ModuleManager;

    move-result-object v0

    invoke-virtual {v0, p0}, Lgnu/expr/ModuleManager;->findWithClassName(Ljava/lang/String;)Lgnu/expr/ModuleInfo;

    move-result-object v0

    invoke-virtual {v0}, Lgnu/expr/ModuleInfo;->getInstance()Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

.method public static importDefinitions(Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z
    .locals 50
    .parameter "className"
    .parameter "info"
    .parameter "renamer"
    .parameter "forms"
    .parameter "defs"
    .parameter "tr"

    .prologue
    .line 229
    invoke-static {}, Lgnu/expr/ModuleManager;->getInstance()Lgnu/expr/ModuleManager;

    move-result-object v30

    .line 231
    .local v30, manager:Lgnu/expr/ModuleManager;
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getState()I

    move-result v46

    and-int/lit8 v46, v46, 0x1

    if-nez v46, :cond_1

    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getCompilation()Lgnu/expr/Compilation;

    move-result-object v46

    if-nez v46, :cond_1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v36

    .local v36, now:J
    move-object/from16 v0, p1

    move-object/from16 v1, v30

    move-wide/from16 v2, v36

    invoke-virtual {v0, v1, v2, v3}, Lgnu/expr/ModuleInfo;->checkCurrent(Lgnu/expr/ModuleManager;J)Z

    move-result v46

    if-nez v46, :cond_1

    .line 235
    invoke-virtual/range {p5 .. p5}, Lgnu/expr/Compilation;->getMessages()Lgnu/text/SourceMessages;

    move-result-object v32

    .line 236
    .local v32, messages:Lgnu/text/SourceMessages;
    invoke-static {}, Lgnu/expr/Language;->getDefaultLanguage()Lgnu/expr/Language;

    move-result-object v29

    .line 240
    .local v29, language:Lgnu/expr/Language;
    :try_start_0
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getSourceAbsPath()Lgnu/text/Path;

    move-result-object v46

    invoke-static/range {v46 .. v46}, Lgnu/mapping/InPort;->openFile(Ljava/lang/Object;)Lgnu/mapping/InPort;

    move-result-object v21

    .line 241
    .local v21, fstream:Lgnu/mapping/InPort;
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->clearClass()V

    .line 242
    move-object/from16 v0, p1

    move-object/from16 v1, p0

    invoke-virtual {v0, v1}, Lgnu/expr/ModuleInfo;->setClassName(Ljava/lang/String;)V

    .line 243
    const/16 v38, 0x8

    .line 244
    .local v38, options:I
    move-object/from16 v0, p5

    iget-boolean v0, v0, Lgnu/expr/Compilation;->immediate:Z

    move/from16 v46, v0

    if-eqz v46, :cond_0

    .line 245
    or-int/lit8 v38, v38, 0x1

    .line 246
    :cond_0
    move-object/from16 v0, v29

    move-object/from16 v1, v21

    move-object/from16 v2, v32

    move/from16 v3, v38

    move-object/from16 v4, p1

    invoke-virtual {v0, v1, v2, v3, v4}, Lgnu/expr/Language;->parse(Lgnu/mapping/InPort;Lgnu/text/SourceMessages;ILgnu/expr/ModuleInfo;)Lgnu/expr/Compilation;
    :try_end_0
    .catch Ljava/io/FileNotFoundException; {:try_start_0 .. :try_end_0} :catch_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_1
    .catch Lgnu/text/SyntaxException; {:try_start_0 .. :try_end_0} :catch_2

    move-result-object v11

    .line 265
    .local v11, comp:Lgnu/expr/Compilation;
    invoke-virtual {v11}, Lgnu/expr/Compilation;->getModule()Lgnu/expr/ModuleExp;

    move-result-object v46

    move-object/from16 v0, v46

    invoke-virtual {v0, v11}, Lgnu/expr/ModuleExp;->classFor(Lgnu/expr/Compilation;)Lgnu/bytecode/ClassType;

    move-result-object v46

    invoke-virtual/range {v46 .. v46}, Lgnu/bytecode/ClassType;->getName()Ljava/lang/String;

    move-result-object v46

    move-object/from16 v0, p1

    move-object/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/ModuleInfo;->setClassName(Ljava/lang/String;)V

    .line 268
    .end local v11           #comp:Lgnu/expr/Compilation;
    .end local v21           #fstream:Lgnu/mapping/InPort;
    .end local v29           #language:Lgnu/expr/Language;
    .end local v32           #messages:Lgnu/text/SourceMessages;
    .end local v36           #now:J
    .end local v38           #options:I
    :cond_1
    move-object/from16 v0, p5

    iget-object v0, v0, Lgnu/expr/Compilation;->minfo:Lgnu/expr/ModuleInfo;

    move-object/from16 v46, v0

    if-eqz v46, :cond_3

    invoke-virtual/range {p5 .. p5}, Lgnu/expr/Compilation;->getState()I

    move-result v46

    const/16 v47, 0x4

    move/from16 v0, v46

    move/from16 v1, v47

    if-ge v0, v1, :cond_3

    .line 270
    move-object/from16 v0, p5

    iget-object v0, v0, Lgnu/expr/Compilation;->minfo:Lgnu/expr/ModuleInfo;

    move-object/from16 v46, v0

    move-object/from16 v0, v46

    move-object/from16 v1, p1

    invoke-virtual {v0, v1}, Lgnu/expr/ModuleInfo;->addDependency(Lgnu/expr/ModuleInfo;)V

    .line 272
    const/16 v46, 0xc

    move-object/from16 v0, p1

    move/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/ModuleInfo;->loadEager(I)Z

    move-result v46

    if-nez v46, :cond_3

    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getState()I

    move-result v46

    const/16 v47, 0x6

    move/from16 v0, v46

    move/from16 v1, v47

    if-ge v0, v1, :cond_3

    .line 276
    invoke-virtual/range {p3 .. p3}, Ljava/util/Vector;->size()I

    move-result v46

    move-object/from16 v0, p5

    move-object/from16 v1, p1

    move-object/from16 v2, p4

    move/from16 v3, v46

    invoke-virtual {v0, v1, v2, v3}, Lgnu/expr/Compilation;->pushPendingImport(Lgnu/expr/ModuleInfo;Lgnu/expr/ScopeExp;I)V

    .line 277
    const/16 v46, 0x1

    .line 490
    :goto_0
    return v46

    .line 248
    .restart local v29       #language:Lgnu/expr/Language;
    .restart local v32       #messages:Lgnu/text/SourceMessages;
    .restart local v36       #now:J
    :catch_0
    move-exception v16

    .line 250
    .local v16, ex:Ljava/io/FileNotFoundException;
    const/16 v46, 0x65

    new-instance v47, Ljava/lang/StringBuilder;

    invoke-direct/range {v47 .. v47}, Ljava/lang/StringBuilder;-><init>()V

    const-string v48, "not found: "

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v16 .. v16}, Ljava/io/FileNotFoundException;->getMessage()Ljava/lang/String;

    move-result-object v48

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v47 .. v47}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v47

    move-object/from16 v0, p5

    move/from16 v1, v46

    move-object/from16 v2, v47

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Compilation;->error(CLjava/lang/String;)V

    .line 251
    const/16 v46, 0x0

    goto :goto_0

    .line 253
    .end local v16           #ex:Ljava/io/FileNotFoundException;
    :catch_1
    move-exception v16

    .line 255
    .local v16, ex:Ljava/io/IOException;
    const/16 v46, 0x65

    new-instance v47, Ljava/lang/StringBuilder;

    invoke-direct/range {v47 .. v47}, Ljava/lang/StringBuilder;-><init>()V

    const-string v48, "caught "

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    move-object/from16 v0, v47

    move-object/from16 v1, v16

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v47 .. v47}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v47

    move-object/from16 v0, p5

    move/from16 v1, v46

    move-object/from16 v2, v47

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Compilation;->error(CLjava/lang/String;)V

    .line 256
    const/16 v46, 0x0

    goto :goto_0

    .line 258
    .end local v16           #ex:Ljava/io/IOException;
    :catch_2
    move-exception v16

    .line 260
    .local v16, ex:Lgnu/text/SyntaxException;
    invoke-virtual/range {v16 .. v16}, Lgnu/text/SyntaxException;->getMessages()Lgnu/text/SourceMessages;

    move-result-object v46

    move-object/from16 v0, v46

    move-object/from16 v1, v32

    if-eq v0, v1, :cond_2

    .line 261
    new-instance v46, Ljava/lang/RuntimeException;

    new-instance v47, Ljava/lang/StringBuilder;

    invoke-direct/range {v47 .. v47}, Ljava/lang/StringBuilder;-><init>()V

    const-string v48, "confussing syntax error: "

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    move-object/from16 v0, v47

    move-object/from16 v1, v16

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v47 .. v47}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v47

    invoke-direct/range {v46 .. v47}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/String;)V

    throw v46

    .line 263
    :cond_2
    const/16 v46, 0x0

    goto :goto_0

    .line 281
    .end local v16           #ex:Lgnu/text/SyntaxException;
    .end local v29           #language:Lgnu/expr/Language;
    .end local v32           #messages:Lgnu/text/SourceMessages;
    .end local v36           #now:J
    :cond_3
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getClassType()Lgnu/bytecode/ClassType;

    move-result-object v44

    .line 282
    .local v44, type:Lgnu/bytecode/ClassType;
    invoke-virtual/range {v44 .. v44}, Lgnu/bytecode/ClassType;->getName()Ljava/lang/String;

    move-result-object v43

    .line 283
    .local v43, tname:Ljava/lang/String;
    invoke-virtual/range {p5 .. p5}, Lgnu/expr/Compilation;->sharedModuleDefs()Z

    move-result v41

    .line 284
    .local v41, sharedModule:Z
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getState()I

    move-result v46

    const/16 v47, 0x6

    move/from16 v0, v46

    move/from16 v1, v47

    if-ge v0, v1, :cond_5

    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->getCompilation()Lgnu/expr/Compilation;

    move-result-object v46

    invoke-virtual/range {v46 .. v46}, Lgnu/expr/Compilation;->makeRunnable()Z

    move-result v27

    .line 287
    .local v27, isRunnable:Z
    :goto_1
    const/4 v12, 0x0

    .line 288
    .local v12, decl:Lgnu/expr/Declaration;
    const-string v46, "kawa.standard.require"

    invoke-static/range {v46 .. v46}, Lgnu/bytecode/ClassType;->make(Ljava/lang/String;)Lgnu/bytecode/ClassType;

    move-result-object v42

    .line 289
    .local v42, thisType:Lgnu/bytecode/ClassType;
    const/16 v46, 0x1

    move/from16 v0, v46

    new-array v9, v0, [Lgnu/expr/Expression;

    const/16 v46, 0x0

    new-instance v47, Lgnu/expr/QuoteExp;

    move-object/from16 v0, v47

    move-object/from16 v1, v43

    invoke-direct {v0, v1}, Lgnu/expr/QuoteExp;-><init>(Ljava/lang/Object;)V

    aput-object v47, v9, v46

    .line 290
    .local v9, args:[Lgnu/expr/Expression;
    const-string v46, "find"

    move-object/from16 v0, v42

    move-object/from16 v1, v46

    invoke-static {v0, v1, v9}, Lgnu/kawa/reflect/Invoke;->makeInvokeStatic(Lgnu/bytecode/ClassType;Ljava/lang/String;[Lgnu/expr/Expression;)Lgnu/expr/ApplyExp;

    move-result-object v14

    .line 291
    .local v14, dofind:Lgnu/expr/Expression;
    const/16 v25, 0x0

    .line 292
    .local v25, instanceField:Lgnu/bytecode/Field;
    invoke-virtual/range {p5 .. p5}, Lgnu/expr/Compilation;->getLanguage()Lgnu/expr/Language;

    move-result-object v29

    .line 293
    .restart local v29       #language:Lgnu/expr/Language;
    move-object/from16 v0, p5

    invoke-virtual {v14, v0}, Lgnu/expr/Expression;->setLine(Lgnu/expr/Compilation;)V

    .line 294
    invoke-virtual/range {p3 .. p3}, Ljava/util/Vector;->size()I

    move-result v19

    .line 296
    .local v19, formsStart:I
    invoke-virtual/range {p1 .. p1}, Lgnu/expr/ModuleInfo;->setupModuleExp()Lgnu/expr/ModuleExp;

    move-result-object v33

    .line 298
    .local v33, mod:Lgnu/expr/ModuleExp;
    new-instance v13, Ljava/util/Vector;

    invoke-direct {v13}, Ljava/util/Vector;-><init>()V

    .line 299
    .local v13, declPairs:Ljava/util/Vector;
    invoke-virtual/range {v33 .. v33}, Lgnu/expr/ModuleExp;->firstDecl()Lgnu/expr/Declaration;

    move-result-object v17

    .line 300
    .local v17, fdecl:Lgnu/expr/Declaration;
    :goto_2
    if-eqz v17, :cond_16

    .line 302
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->isPrivate()Z

    move-result v46

    if-eqz v46, :cond_6

    .line 300
    :cond_4
    :goto_3
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->nextDecl()Lgnu/expr/Declaration;

    move-result-object v17

    goto :goto_2

    .line 284
    .end local v9           #args:[Lgnu/expr/Expression;
    .end local v12           #decl:Lgnu/expr/Declaration;
    .end local v13           #declPairs:Ljava/util/Vector;
    .end local v14           #dofind:Lgnu/expr/Expression;
    .end local v17           #fdecl:Lgnu/expr/Declaration;
    .end local v19           #formsStart:I
    .end local v25           #instanceField:Lgnu/bytecode/Field;
    .end local v27           #isRunnable:Z
    .end local v29           #language:Lgnu/expr/Language;
    .end local v33           #mod:Lgnu/expr/ModuleExp;
    .end local v42           #thisType:Lgnu/bytecode/ClassType;
    :cond_5
    sget-object v46, Lgnu/expr/Compilation;->typeRunnable:Lgnu/bytecode/ClassType;

    move-object/from16 v0, v44

    move-object/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/bytecode/ClassType;->isSubtype(Lgnu/bytecode/Type;)Z

    move-result v27

    goto :goto_1

    .line 305
    .restart local v9       #args:[Lgnu/expr/Expression;
    .restart local v12       #decl:Lgnu/expr/Declaration;
    .restart local v13       #declPairs:Ljava/util/Vector;
    .restart local v14       #dofind:Lgnu/expr/Expression;
    .restart local v17       #fdecl:Lgnu/expr/Declaration;
    .restart local v19       #formsStart:I
    .restart local v25       #instanceField:Lgnu/bytecode/Field;
    .restart local v27       #isRunnable:Z
    .restart local v29       #language:Lgnu/expr/Language;
    .restart local v33       #mod:Lgnu/expr/ModuleExp;
    .restart local v42       #thisType:Lgnu/bytecode/ClassType;
    :cond_6
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->getSymbol()Ljava/lang/Object;

    move-result-object v7

    check-cast v7, Lgnu/mapping/Symbol;

    .line 306
    .local v7, aname:Lgnu/mapping/Symbol;
    if-eqz p2, :cond_8

    .line 311
    :try_start_1
    move-object/from16 v0, p2

    invoke-virtual {v0, v7}, Lgnu/mapping/Procedure;->apply1(Ljava/lang/Object;)Ljava/lang/Object;
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_3

    move-result-object v31

    .line 317
    :goto_4
    if-eqz v31, :cond_4

    .line 319
    move-object/from16 v0, v31

    instance-of v0, v0, Lgnu/mapping/Symbol;

    move/from16 v46, v0

    if-nez v46, :cond_7

    .line 321
    const/16 v46, 0x65

    new-instance v47, Ljava/lang/StringBuilder;

    invoke-direct/range {v47 .. v47}, Ljava/lang/StringBuilder;-><init>()V

    const-string v48, "internal error - import name mapper returned non-symbol: "

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v31 .. v31}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v48

    invoke-virtual/range {v48 .. v48}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v48

    invoke-virtual/range {v47 .. v48}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v47

    invoke-virtual/range {v47 .. v47}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v47

    move-object/from16 v0, p5

    move/from16 v1, v46

    move-object/from16 v2, v47

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Compilation;->error(CLjava/lang/String;)V

    goto :goto_3

    .line 313
    :catch_3
    move-exception v16

    .line 315
    .local v16, ex:Ljava/lang/Throwable;
    move-object/from16 v31, v16

    .local v31, mapped:Ljava/lang/Throwable;
    goto :goto_4

    .end local v16           #ex:Ljava/lang/Throwable;
    .end local v31           #mapped:Ljava/lang/Throwable;
    :cond_7
    move-object/from16 v7, v31

    .line 324
    check-cast v7, Lgnu/mapping/Symbol;

    .line 326
    :cond_8
    const-wide/16 v46, 0x800

    move-object/from16 v0, v17

    move-wide/from16 v1, v46

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Declaration;->getFlag(J)Z

    move-result v28

    .line 327
    .local v28, isStatic:Z
    if-nez v28, :cond_a

    if-nez v12, :cond_a

    .line 329
    new-instance v46, Ljava/lang/StringBuilder;

    invoke-direct/range {v46 .. v46}, Ljava/lang/StringBuilder;-><init>()V

    const/16 v47, 0x2e

    const/16 v48, 0x24

    move-object/from16 v0, v43

    move/from16 v1, v47

    move/from16 v2, v48

    invoke-virtual {v0, v1, v2}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object v47

    invoke-virtual/range {v46 .. v47}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v46

    const-string v47, "$instance"

    invoke-virtual/range {v46 .. v47}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v46

    invoke-virtual/range {v46 .. v46}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v24

    .line 330
    .local v24, iname:Ljava/lang/String;
    new-instance v12, Lgnu/expr/Declaration;

    .end local v12           #decl:Lgnu/expr/Declaration;
    invoke-static/range {v24 .. v24}, Lgnu/mapping/SimpleSymbol;->valueOf(Ljava/lang/String;)Lgnu/mapping/SimpleSymbol;

    move-result-object v46

    move-object/from16 v0, v46

    move-object/from16 v1, v44

    invoke-direct {v12, v0, v1}, Lgnu/expr/Declaration;-><init>(Ljava/lang/Object;Lgnu/bytecode/Type;)V

    .line 331
    .restart local v12       #decl:Lgnu/expr/Declaration;
    const/16 v46, 0x1

    move/from16 v0, v46

    invoke-virtual {v12, v0}, Lgnu/expr/Declaration;->setPrivate(Z)V

    .line 332
    const-wide/32 v46, 0x40004000

    move-wide/from16 v0, v46

    invoke-virtual {v12, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 334
    move-object/from16 v0, p4

    invoke-virtual {v0, v12}, Lgnu/expr/ScopeExp;->addDeclaration(Lgnu/expr/Declaration;)V

    .line 336
    invoke-virtual {v12, v14}, Lgnu/expr/Declaration;->noteValue(Lgnu/expr/Expression;)V

    .line 337
    new-instance v40, Lgnu/expr/SetExp;

    move-object/from16 v0, v40

    invoke-direct {v0, v12, v14}, Lgnu/expr/SetExp;-><init>(Lgnu/expr/Declaration;Lgnu/expr/Expression;)V

    .line 338
    .local v40, sexp:Lgnu/expr/SetExp;
    move-object/from16 v0, v40

    move-object/from16 v1, p5

    invoke-virtual {v0, v1}, Lgnu/expr/SetExp;->setLine(Lgnu/expr/Compilation;)V

    .line 339
    const/16 v46, 0x1

    move-object/from16 v0, v40

    move/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/SetExp;->setDefining(Z)V

    .line 340
    move-object/from16 v0, p3

    move-object/from16 v1, v40

    invoke-virtual {v0, v1}, Ljava/util/Vector;->addElement(Ljava/lang/Object;)V

    .line 341
    invoke-virtual/range {p3 .. p3}, Ljava/util/Vector;->size()I

    move-result v19

    .line 342
    const-wide/32 v46, 0x20000000

    move-wide/from16 v0, v46

    invoke-virtual {v12, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 345
    if-eqz v27, :cond_9

    .line 346
    const/16 v46, 0x0

    move/from16 v0, v46

    invoke-virtual {v12, v0}, Lgnu/expr/Declaration;->setSimple(Z)V

    .line 348
    :cond_9
    const-wide/16 v46, 0x2000

    move-wide/from16 v0, v46

    invoke-virtual {v12, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 351
    .end local v24           #iname:Ljava/lang/String;
    .end local v40           #sexp:Lgnu/expr/SetExp;
    :cond_a
    move-object/from16 v0, v17

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v46, v0

    if-eqz v46, :cond_b

    .line 353
    move-object/from16 v0, v17

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v46, v0

    invoke-virtual/range {v46 .. v46}, Lgnu/bytecode/Field;->getName()Ljava/lang/String;

    move-result-object v18

    .line 354
    .local v18, fname:Ljava/lang/String;
    const-string v46, "$instance"

    move-object/from16 v0, v18

    move-object/from16 v1, v46

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v46

    if-eqz v46, :cond_b

    .line 356
    move-object/from16 v0, v17

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v25, v0

    .line 357
    goto/16 :goto_3

    .line 368
    .end local v18           #fname:Ljava/lang/String;
    :cond_b
    move-object/from16 v0, v17

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v46, v0

    if-eqz v46, :cond_10

    move-object/from16 v0, v17

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v46, v0

    invoke-virtual/range {v46 .. v46}, Lgnu/bytecode/Field;->getName()Ljava/lang/String;

    move-result-object v46

    const-string v47, "$instance"

    invoke-virtual/range {v46 .. v47}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v46

    if-eqz v46, :cond_10

    const/16 v26, 0x1

    .line 372
    .local v26, isImportedInstance:Z
    :goto_5
    move-object/from16 v0, v29

    move-object/from16 v1, v17

    invoke-virtual {v0, v1}, Lgnu/expr/Language;->getNamespaceOf(Lgnu/expr/Declaration;)I

    move-result v46

    move-object/from16 v0, p4

    move-object/from16 v1, v29

    move/from16 v2, v46

    invoke-virtual {v0, v7, v1, v2}, Lgnu/expr/ScopeExp;->lookup(Ljava/lang/Object;Lgnu/expr/Language;I)Lgnu/expr/Declaration;

    move-result-object v35

    .line 373
    .local v35, old:Lgnu/expr/Declaration;
    if-eqz v26, :cond_11

    .line 375
    if-nez v35, :cond_4

    .line 377
    move-object/from16 v0, p4

    invoke-virtual {v0, v7}, Lgnu/expr/ScopeExp;->addDeclaration(Ljava/lang/Object;)Lgnu/expr/Declaration;

    move-result-object v6

    .line 378
    .local v6, adecl:Lgnu/expr/Declaration;
    const-wide/32 v46, 0x40004000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 380
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->getType()Lgnu/bytecode/Type;

    move-result-object v46

    move-object/from16 v0, v46

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setType(Lgnu/bytecode/Type;)V

    .line 381
    const-wide/16 v46, 0x2000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 405
    :goto_6
    move-object/from16 v0, p5

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setLocation(Lgnu/text/SourceLocator;)V

    .line 406
    new-instance v20, Lgnu/expr/ReferenceExp;

    move-object/from16 v0, v20

    move-object/from16 v1, v17

    invoke-direct {v0, v1}, Lgnu/expr/ReferenceExp;-><init>(Lgnu/expr/Declaration;)V

    .line 407
    .local v20, fref:Lgnu/expr/ReferenceExp;
    move-object/from16 v0, v20

    invoke-virtual {v0, v12}, Lgnu/expr/ReferenceExp;->setContextDecl(Lgnu/expr/Declaration;)V

    .line 408
    if-nez v26, :cond_c

    .line 410
    const/16 v46, 0x1

    move-object/from16 v0, v20

    move/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/ReferenceExp;->setDontDereference(Z)V

    .line 411
    if-nez v41, :cond_c

    .line 412
    const/16 v46, 0x1

    move/from16 v0, v46

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setPrivate(Z)V

    .line 415
    :cond_c
    const-wide/16 v46, 0x4000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 416
    const-wide/32 v46, 0x8000

    move-object/from16 v0, v17

    move-wide/from16 v1, v46

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Declaration;->getFlag(J)Z

    move-result v46

    if-eqz v46, :cond_d

    .line 417
    const-wide/32 v46, 0x8000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 418
    :cond_d
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->isProcedureDecl()Z

    move-result v46

    if-eqz v46, :cond_e

    .line 419
    const/16 v46, 0x1

    move/from16 v0, v46

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setProcedureDecl(Z)V

    .line 420
    :cond_e
    if-eqz v28, :cond_f

    .line 421
    const-wide/16 v46, 0x800

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 423
    :cond_f
    new-instance v40, Lgnu/expr/SetExp;

    move-object/from16 v0, v40

    move-object/from16 v1, v20

    invoke-direct {v0, v6, v1}, Lgnu/expr/SetExp;-><init>(Lgnu/expr/Declaration;Lgnu/expr/Expression;)V

    .line 424
    .restart local v40       #sexp:Lgnu/expr/SetExp;
    const-wide/32 v46, 0x20000000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 425
    const/16 v46, 0x1

    move-object/from16 v0, v40

    move/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/SetExp;->setDefining(Z)V

    .line 426
    if-eqz v26, :cond_15

    .line 432
    move-object/from16 v0, p3

    move-object/from16 v1, v40

    move/from16 v2, v19

    invoke-virtual {v0, v1, v2}, Ljava/util/Vector;->insertElementAt(Ljava/lang/Object;I)V

    .line 433
    add-int/lit8 v19, v19, 0x1

    .line 438
    :goto_7
    invoke-virtual {v13, v6}, Ljava/util/Vector;->add(Ljava/lang/Object;)Z

    .line 439
    move-object/from16 v0, v17

    invoke-virtual {v13, v0}, Ljava/util/Vector;->add(Ljava/lang/Object;)Z

    .line 441
    move-object/from16 v0, v20

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->noteValue(Lgnu/expr/Expression;)V

    .line 442
    const-wide/32 v46, 0x20000

    move-wide/from16 v0, v46

    invoke-virtual {v6, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 443
    move-object/from16 v0, p5

    invoke-virtual {v0, v6}, Lgnu/expr/Compilation;->push(Lgnu/expr/Declaration;)V

    goto/16 :goto_3

    .line 368
    .end local v6           #adecl:Lgnu/expr/Declaration;
    .end local v20           #fref:Lgnu/expr/ReferenceExp;
    .end local v26           #isImportedInstance:Z
    .end local v35           #old:Lgnu/expr/Declaration;
    .end local v40           #sexp:Lgnu/expr/SetExp;
    :cond_10
    const/16 v26, 0x0

    goto/16 :goto_5

    .line 383
    .restart local v26       #isImportedInstance:Z
    .restart local v35       #old:Lgnu/expr/Declaration;
    :cond_11
    if-eqz v35, :cond_12

    const-wide/16 v46, 0x200

    move-object/from16 v0, v35

    move-wide/from16 v1, v46

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Declaration;->getFlag(J)Z

    move-result v46

    if-nez v46, :cond_12

    invoke-static/range {v35 .. v35}, Lgnu/expr/Declaration;->followAliases(Lgnu/expr/Declaration;)Lgnu/expr/Declaration;

    move-result-object v46

    invoke-static/range {v17 .. v17}, Lgnu/expr/Declaration;->followAliases(Lgnu/expr/Declaration;)Lgnu/expr/Declaration;

    move-result-object v47

    move-object/from16 v0, v46

    move-object/from16 v1, v47

    if-eq v0, v1, :cond_4

    .line 390
    :cond_12
    if-eqz v35, :cond_14

    const-wide/32 v46, 0x10200

    move-object/from16 v0, v35

    move-wide/from16 v1, v46

    invoke-virtual {v0, v1, v2}, Lgnu/expr/Declaration;->getFlag(J)Z

    move-result v46

    if-eqz v46, :cond_14

    .line 393
    const/16 v46, 0x0

    const-wide/32 v48, 0x10200

    move-object/from16 v0, v35

    move/from16 v1, v46

    move-wide/from16 v2, v48

    invoke-virtual {v0, v1, v2, v3}, Lgnu/expr/Declaration;->setFlag(ZJ)V

    .line 394
    move-object/from16 v6, v35

    .line 402
    .restart local v6       #adecl:Lgnu/expr/Declaration;
    :cond_13
    :goto_8
    const/16 v46, 0x1

    move/from16 v0, v46

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setAlias(Z)V

    .line 403
    const/16 v46, 0x1

    move/from16 v0, v46

    invoke-virtual {v6, v0}, Lgnu/expr/Declaration;->setIndirectBinding(Z)V

    goto/16 :goto_6

    .line 398
    .end local v6           #adecl:Lgnu/expr/Declaration;
    :cond_14
    move-object/from16 v0, p4

    invoke-virtual {v0, v7}, Lgnu/expr/ScopeExp;->addDeclaration(Ljava/lang/Object;)Lgnu/expr/Declaration;

    move-result-object v6

    .line 399
    .restart local v6       #adecl:Lgnu/expr/Declaration;
    if-eqz v35, :cond_13

    .line 400
    move-object/from16 v0, v35

    move-object/from16 v1, p5

    invoke-static {v0, v6, v1}, Lgnu/expr/ScopeExp;->duplicateDeclarationError(Lgnu/expr/Declaration;Lgnu/expr/Declaration;Lgnu/expr/Compilation;)V

    goto :goto_8

    .line 436
    .restart local v20       #fref:Lgnu/expr/ReferenceExp;
    .restart local v40       #sexp:Lgnu/expr/SetExp;
    :cond_15
    move-object/from16 v0, p3

    move-object/from16 v1, v40

    invoke-virtual {v0, v1}, Ljava/util/Vector;->addElement(Ljava/lang/Object;)V

    goto/16 :goto_7

    .line 448
    .end local v6           #adecl:Lgnu/expr/Declaration;
    .end local v7           #aname:Lgnu/mapping/Symbol;
    .end local v20           #fref:Lgnu/expr/ReferenceExp;
    .end local v26           #isImportedInstance:Z
    .end local v28           #isStatic:Z
    .end local v35           #old:Lgnu/expr/Declaration;
    .end local v40           #sexp:Lgnu/expr/SetExp;
    :cond_16
    invoke-virtual {v13}, Ljava/util/Vector;->size()I

    move-result v34

    .line 449
    .local v34, ndecls:I
    const/16 v23, 0x0

    .local v23, i:I
    :goto_9
    move/from16 v0, v23

    move/from16 v1, v34

    if-ge v0, v1, :cond_18

    .line 451
    move/from16 v0, v23

    invoke-virtual {v13, v0}, Ljava/util/Vector;->elementAt(I)Ljava/lang/Object;

    move-result-object v6

    check-cast v6, Lgnu/expr/Declaration;

    .line 452
    .restart local v6       #adecl:Lgnu/expr/Declaration;
    add-int/lit8 v46, v23, 0x1

    move/from16 v0, v46

    invoke-virtual {v13, v0}, Ljava/util/Vector;->elementAt(I)Ljava/lang/Object;

    move-result-object v17

    .end local v17           #fdecl:Lgnu/expr/Declaration;
    check-cast v17, Lgnu/expr/Declaration;

    .line 453
    .restart local v17       #fdecl:Lgnu/expr/Declaration;
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->getValue()Lgnu/expr/Expression;

    move-result-object v22

    .line 454
    .local v22, fval:Lgnu/expr/Expression;
    invoke-virtual/range {v17 .. v17}, Lgnu/expr/Declaration;->isIndirectBinding()Z

    move-result v46

    if-eqz v46, :cond_17

    move-object/from16 v0, v22

    instance-of v0, v0, Lgnu/expr/ReferenceExp;

    move/from16 v46, v0

    if-eqz v46, :cond_17

    .line 456
    invoke-virtual {v6}, Lgnu/expr/Declaration;->getValue()Lgnu/expr/Expression;

    move-result-object v8

    check-cast v8, Lgnu/expr/ReferenceExp;

    .line 457
    .local v8, aref:Lgnu/expr/ReferenceExp;
    check-cast v22, Lgnu/expr/ReferenceExp;

    .end local v22           #fval:Lgnu/expr/Expression;
    invoke-virtual/range {v22 .. v22}, Lgnu/expr/ReferenceExp;->getBinding()Lgnu/expr/Declaration;

    move-result-object v45

    .line 458
    .local v45, xdecl:Lgnu/expr/Declaration;
    move-object/from16 v0, v45

    invoke-virtual {v8, v0}, Lgnu/expr/ReferenceExp;->setBinding(Lgnu/expr/Declaration;)V

    .line 459
    invoke-virtual/range {v45 .. v45}, Lgnu/expr/Declaration;->needsContext()Z

    move-result v46

    if-eqz v46, :cond_17

    .line 461
    new-instance v46, Ljava/lang/StringBuilder;

    invoke-direct/range {v46 .. v46}, Ljava/lang/StringBuilder;-><init>()V

    move-object/from16 v0, v45

    iget-object v0, v0, Lgnu/expr/Declaration;->field:Lgnu/bytecode/Field;

    move-object/from16 v47, v0

    invoke-virtual/range {v47 .. v47}, Lgnu/bytecode/Field;->getDeclaringClass()Lgnu/bytecode/ClassType;

    move-result-object v47

    invoke-virtual/range {v47 .. v47}, Lgnu/bytecode/ClassType;->getName()Ljava/lang/String;

    move-result-object v47

    const/16 v48, 0x2e

    const/16 v49, 0x24

    invoke-virtual/range {v47 .. v49}, Ljava/lang/String;->replace(CC)Ljava/lang/String;

    move-result-object v47

    invoke-virtual/range {v46 .. v47}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v46

    const-string v47, "$instance"

    invoke-virtual/range {v46 .. v47}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v46

    invoke-virtual/range {v46 .. v46}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v24

    .line 464
    .restart local v24       #iname:Ljava/lang/String;
    invoke-static/range {v24 .. v24}, Lgnu/mapping/SimpleSymbol;->valueOf(Ljava/lang/String;)Lgnu/mapping/SimpleSymbol;

    move-result-object v46

    move-object/from16 v0, p4

    move-object/from16 v1, v46

    invoke-virtual {v0, v1}, Lgnu/expr/ScopeExp;->lookup(Ljava/lang/Object;)Lgnu/expr/Declaration;

    move-result-object v10

    .line 465
    .local v10, cdecl:Lgnu/expr/Declaration;
    const-wide/16 v46, 0x400

    move-wide/from16 v0, v46

    invoke-virtual {v10, v0, v1}, Lgnu/expr/Declaration;->setFlag(J)V

    .line 466
    invoke-virtual {v8, v10}, Lgnu/expr/ReferenceExp;->setContextDecl(Lgnu/expr/Declaration;)V

    .line 449
    .end local v8           #aref:Lgnu/expr/ReferenceExp;
    .end local v10           #cdecl:Lgnu/expr/Declaration;
    .end local v24           #iname:Ljava/lang/String;
    .end local v45           #xdecl:Lgnu/expr/Declaration;
    :cond_17
    add-int/lit8 v23, v23, 0x2

    goto/16 :goto_9

    .line 471
    .end local v6           #adecl:Lgnu/expr/Declaration;
    :cond_18
    if-eqz v27, :cond_1a

    .line 473
    sget-object v46, Lgnu/expr/Compilation;->typeRunnable:Lgnu/bytecode/ClassType;

    const-string v47, "run"

    const/16 v48, 0x0

    invoke-virtual/range {v46 .. v48}, Lgnu/bytecode/ClassType;->getDeclaredMethod(Ljava/lang/String;I)Lgnu/bytecode/Method;

    move-result-object v39

    .line 474
    .local v39, run:Lgnu/bytecode/Method;
    if-eqz v12, :cond_1b

    .line 475
    new-instance v14, Lgnu/expr/ReferenceExp;

    .end local v14           #dofind:Lgnu/expr/Expression;
    invoke-direct {v14, v12}, Lgnu/expr/ReferenceExp;-><init>(Lgnu/expr/Declaration;)V

    .line 485
    .restart local v14       #dofind:Lgnu/expr/Expression;
    :cond_19
    :goto_a
    new-instance v15, Lgnu/expr/ApplyExp;

    const/16 v46, 0x1

    move/from16 v0, v46

    new-array v0, v0, [Lgnu/expr/Expression;

    move-object/from16 v46, v0

    const/16 v47, 0x0

    aput-object v14, v46, v47

    move-object/from16 v0, v39

    move-object/from16 v1, v46

    invoke-direct {v15, v0, v1}, Lgnu/expr/ApplyExp;-><init>(Lgnu/bytecode/Method;[Lgnu/expr/Expression;)V

    .line 486
    .end local v14           #dofind:Lgnu/expr/Expression;
    .local v15, dofind:Lgnu/expr/Expression;
    move-object/from16 v0, p5

    invoke-virtual {v15, v0}, Lgnu/expr/Expression;->setLine(Lgnu/expr/Compilation;)V

    .line 487
    move-object/from16 v0, p3

    invoke-virtual {v0, v15}, Ljava/util/Vector;->addElement(Ljava/lang/Object;)V

    move-object v14, v15

    .line 490
    .end local v15           #dofind:Lgnu/expr/Expression;
    .end local v39           #run:Lgnu/bytecode/Method;
    .restart local v14       #dofind:Lgnu/expr/Expression;
    :cond_1a
    const/16 v46, 0x1

    goto/16 :goto_0

    .line 478
    .restart local v39       #run:Lgnu/bytecode/Method;
    :cond_1b
    if-eqz v25, :cond_19

    .line 480
    const/16 v46, 0x2

    move/from16 v0, v46

    new-array v9, v0, [Lgnu/expr/Expression;

    .end local v9           #args:[Lgnu/expr/Expression;
    const/16 v46, 0x0

    new-instance v47, Lgnu/expr/QuoteExp;

    move-object/from16 v0, v47

    move-object/from16 v1, v44

    invoke-direct {v0, v1}, Lgnu/expr/QuoteExp;-><init>(Ljava/lang/Object;)V

    aput-object v47, v9, v46

    const/16 v46, 0x1

    new-instance v47, Lgnu/expr/QuoteExp;

    const-string v48, "$instance"

    invoke-direct/range {v47 .. v48}, Lgnu/expr/QuoteExp;-><init>(Ljava/lang/Object;)V

    aput-object v47, v9, v46

    .line 482
    .restart local v9       #args:[Lgnu/expr/Expression;
    new-instance v14, Lgnu/expr/ApplyExp;

    .end local v14           #dofind:Lgnu/expr/Expression;
    sget-object v46, Lgnu/kawa/reflect/SlotGet;->staticField:Lgnu/kawa/reflect/SlotGet;

    move-object/from16 v0, v46

    invoke-direct {v14, v0, v9}, Lgnu/expr/ApplyExp;-><init>(Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V

    .restart local v14       #dofind:Lgnu/expr/Expression;
    goto :goto_a
.end method

.method public static lookupModuleFromSourcePath(Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;
    .locals 3
    .parameter "sourceName"
    .parameter "defs"

    .prologue
    .line 214
    invoke-static {}, Lgnu/expr/ModuleManager;->getInstance()Lgnu/expr/ModuleManager;

    move-result-object v1

    .line 215
    .local v1, manager:Lgnu/expr/ModuleManager;
    invoke-virtual {p1}, Lgnu/expr/ScopeExp;->getFileName()Ljava/lang/String;

    move-result-object v0

    .line 216
    .local v0, baseName:Ljava/lang/String;
    if-eqz v0, :cond_0

    .line 217
    invoke-static {v0}, Lgnu/text/Path;->valueOf(Ljava/lang/Object;)Lgnu/text/Path;

    move-result-object v2

    invoke-virtual {v2, p0}, Lgnu/text/Path;->resolve(Ljava/lang/String;)Lgnu/text/Path;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    .line 218
    :cond_0
    invoke-virtual {v1, p0}, Lgnu/expr/ModuleManager;->findWithSourcePath(Ljava/lang/String;)Lgnu/expr/ModuleInfo;

    move-result-object v2

    return-object v2
.end method

.method static map(Ljava/lang/String;Ljava/lang/String;)V
    .locals 1
    .parameter "featureName"
    .parameter "className"

    .prologue
    .line 63
    sget-object v0, Lkawa/standard/require;->featureMap:Ljava/util/Hashtable;

    invoke-virtual {v0, p0, p1}, Ljava/util/Hashtable;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 64
    return-void
.end method

.method public static mapFeature(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .parameter "featureName"

    .prologue
    .line 108
    sget-object v0, Lkawa/standard/require;->featureMap:Ljava/util/Hashtable;

    invoke-virtual {v0, p0}, Ljava/util/Hashtable;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    return-object v0
.end method


# virtual methods
.method public rewriteForm(Lgnu/lists/Pair;Lkawa/lang/Translator;)Lgnu/expr/Expression;
    .locals 1
    .parameter "form"
    .parameter "tr"

    .prologue
    .line 495
    const/4 v0, 0x0

    return-object v0
.end method

.method public scanForDefinitions(Lgnu/lists/Pair;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lkawa/lang/Translator;)Z
    .locals 16
    .parameter "st"
    .parameter "forms"
    .parameter "defs"
    .parameter "tr"

    .prologue
    .line 120
    invoke-virtual/range {p4 .. p4}, Lkawa/lang/Translator;->getState()I

    move-result v2

    const/4 v4, 0x1

    if-ne v2, v4, :cond_0

    .line 122
    const/4 v2, 0x2

    move-object/from16 v0, p4

    invoke-virtual {v0, v2}, Lkawa/lang/Translator;->setState(I)V

    .line 123
    move-object/from16 v0, p1

    move-object/from16 v1, p4

    iput-object v0, v1, Lkawa/lang/Translator;->pendingForm:Ljava/lang/Object;

    .line 126
    const/4 v2, 0x1

    .line 209
    :goto_0
    return v2

    .line 128
    :cond_0
    invoke-virtual/range {p1 .. p1}, Lgnu/lists/Pair;->getCdr()Ljava/lang/Object;

    move-result-object v10

    check-cast v10, Lgnu/lists/Pair;

    .line 129
    .local v10, args:Lgnu/lists/Pair;
    invoke-virtual {v10}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v12

    .line 130
    .local v12, name:Ljava/lang/Object;
    const/4 v15, 0x0

    .line 132
    .local v15, type:Lgnu/bytecode/Type;
    instance-of v2, v12, Lgnu/lists/Pair;

    if-eqz v2, :cond_5

    move-object v13, v12

    check-cast v13, Lgnu/lists/Pair;

    .local v13, p:Lgnu/lists/Pair;
    invoke-virtual {v13}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v2

    const-string v4, "quote"

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->matches(Ljava/lang/Object;Ljava/lang/String;)Z

    move-result v2

    if-eqz v2, :cond_5

    .line 135
    invoke-virtual {v13}, Lgnu/lists/Pair;->getCdr()Ljava/lang/Object;

    move-result-object v12

    .line 136
    instance-of v2, v12, Lgnu/lists/Pair;

    if-eqz v2, :cond_1

    move-object v13, v12

    check-cast v13, Lgnu/lists/Pair;

    invoke-virtual {v13}, Lgnu/lists/Pair;->getCdr()Ljava/lang/Object;

    move-result-object v2

    sget-object v4, Lgnu/lists/LList;->Empty:Lgnu/lists/LList;

    if-ne v2, v4, :cond_1

    invoke-virtual {v13}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v2

    instance-of v2, v2, Lgnu/mapping/Symbol;

    if-nez v2, :cond_2

    .line 140
    :cond_1
    const/16 v2, 0x65

    const-string v4, "invalid quoted symbol for \'require\'"

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 141
    const/4 v2, 0x0

    goto :goto_0

    .line 143
    :cond_2
    invoke-virtual {v13}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Lkawa/standard/require;->mapFeature(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v12

    .line 144
    .local v12, name:Ljava/lang/String;
    if-nez v12, :cond_3

    .line 146
    const/16 v2, 0x65

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "unknown feature name \'"

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v13}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v6

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v6, "\' for \'require\'"

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 147
    const/4 v2, 0x0

    goto :goto_0

    .line 149
    :cond_3
    check-cast v12, Ljava/lang/String;

    .end local v12           #name:Ljava/lang/String;
    invoke-static {v12}, Lgnu/bytecode/ClassType;->make(Ljava/lang/String;)Lgnu/bytecode/ClassType;

    move-result-object v15

    .line 192
    .end local v13           #p:Lgnu/lists/Pair;
    :cond_4
    instance-of v2, v15, Lgnu/bytecode/ClassType;

    if-nez v2, :cond_9

    .line 194
    const/16 v2, 0x65

    const-string v4, "invalid specifier for \'require\'"

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 195
    const/4 v2, 0x0

    goto/16 :goto_0

    .line 151
    .local v12, name:Ljava/lang/Object;
    :cond_5
    instance-of v2, v12, Ljava/lang/CharSequence;

    if-eqz v2, :cond_7

    .line 158
    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v14

    .line 159
    .local v14, sourceName:Ljava/lang/String;
    move-object/from16 v0, p3

    invoke-static {v14, v0}, Lkawa/standard/require;->lookupModuleFromSourcePath(Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;

    move-result-object v3

    .line 160
    .local v3, info:Lgnu/expr/ModuleInfo;
    if-nez v3, :cond_6

    .line 162
    const/16 v2, 0x65

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "malformed URL: "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 163
    const/4 v2, 0x0

    goto/16 :goto_0

    .line 165
    :cond_6
    const/4 v2, 0x0

    const/4 v4, 0x0

    move-object/from16 v5, p2

    move-object/from16 v6, p3

    move-object/from16 v7, p4

    invoke-static/range {v2 .. v7}, Lkawa/standard/require;->importDefinitions(Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z

    move-result v2

    goto/16 :goto_0

    .line 167
    .end local v3           #info:Lgnu/expr/ModuleInfo;
    .end local v14           #sourceName:Ljava/lang/String;
    :cond_7
    instance-of v2, v12, Lgnu/mapping/Symbol;

    if-eqz v2, :cond_4

    move-object/from16 v0, p4

    invoke-virtual {v0, v12}, Lkawa/lang/Translator;->selfEvaluatingSymbol(Ljava/lang/Object;)Z

    move-result v2

    if-nez v2, :cond_4

    .line 169
    invoke-virtual/range {p4 .. p4}, Lkawa/lang/Translator;->getLanguage()Lgnu/expr/Language;

    move-result-object v2

    const/4 v4, 0x0

    move-object/from16 v0, p4

    invoke-virtual {v0, v12, v4}, Lkawa/lang/Translator;->rewrite(Ljava/lang/Object;Z)Lgnu/expr/Expression;

    move-result-object v4

    invoke-virtual {v2, v4}, Lgnu/expr/Language;->getTypeFor(Lgnu/expr/Expression;)Lgnu/bytecode/Type;

    move-result-object v15

    .line 170
    instance-of v2, v15, Lgnu/bytecode/ClassType;

    if-eqz v2, :cond_4

    invoke-virtual {v10}, Lgnu/lists/Pair;->getCdr()Ljava/lang/Object;

    move-result-object v2

    instance-of v2, v2, Lgnu/lists/Pair;

    if-eqz v2, :cond_4

    .line 172
    invoke-virtual {v10}, Lgnu/lists/Pair;->getCdr()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lgnu/lists/Pair;

    invoke-virtual {v2}, Lgnu/lists/Pair;->getCar()Ljava/lang/Object;

    move-result-object v12

    .line 173
    instance-of v2, v12, Ljava/lang/CharSequence;

    if-eqz v2, :cond_4

    .line 180
    invoke-virtual {v12}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v14

    .line 181
    .restart local v14       #sourceName:Ljava/lang/String;
    move-object/from16 v0, p3

    invoke-static {v14, v0}, Lkawa/standard/require;->lookupModuleFromSourcePath(Ljava/lang/String;Lgnu/expr/ScopeExp;)Lgnu/expr/ModuleInfo;

    move-result-object v3

    .line 182
    .restart local v3       #info:Lgnu/expr/ModuleInfo;
    if-nez v3, :cond_8

    .line 184
    const/16 v2, 0x65

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "malformed URL: "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v14}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 185
    const/4 v2, 0x0

    goto/16 :goto_0

    .line 187
    :cond_8
    invoke-virtual {v15}, Lgnu/bytecode/Type;->getName()Ljava/lang/String;

    move-result-object v2

    const/4 v4, 0x0

    move-object/from16 v5, p2

    move-object/from16 v6, p3

    move-object/from16 v7, p4

    invoke-static/range {v2 .. v7}, Lkawa/standard/require;->importDefinitions(Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z

    move-result v2

    goto/16 :goto_0

    .line 200
    .end local v3           #info:Lgnu/expr/ModuleInfo;
    .end local v12           #name:Ljava/lang/Object;
    .end local v14           #sourceName:Ljava/lang/String;
    :cond_9
    :try_start_0
    move-object v0, v15

    check-cast v0, Lgnu/bytecode/ClassType;

    move-object v2, v0

    invoke-static {v2}, Lgnu/expr/ModuleInfo;->find(Lgnu/bytecode/ClassType;)Lgnu/expr/ModuleInfo;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result-object v5

    .line 207
    .local v5, minfo:Lgnu/expr/ModuleInfo;
    const/4 v4, 0x0

    const/4 v6, 0x0

    move-object/from16 v7, p2

    move-object/from16 v8, p3

    move-object/from16 v9, p4

    invoke-static/range {v4 .. v9}, Lkawa/standard/require;->importDefinitions(Ljava/lang/String;Lgnu/expr/ModuleInfo;Lgnu/mapping/Procedure;Ljava/util/Vector;Lgnu/expr/ScopeExp;Lgnu/expr/Compilation;)Z

    .line 209
    const/4 v2, 0x1

    goto/16 :goto_0

    .line 202
    .end local v5           #minfo:Lgnu/expr/ModuleInfo;
    :catch_0
    move-exception v11

    .line 204
    .local v11, ex:Ljava/lang/Exception;
    const/16 v2, 0x65

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "unknown class "

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v15}, Lgnu/bytecode/Type;->getName()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    move-object/from16 v0, p4

    invoke-virtual {v0, v2, v4}, Lkawa/lang/Translator;->error(CLjava/lang/String;)V

    .line 205
    const/4 v2, 0x0

    goto/16 :goto_0
.end method
