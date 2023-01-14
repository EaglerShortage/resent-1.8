package net.lax1dude.eaglercraft.v1_8.internal.teavm;

import static net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL.*;
import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;

import net.lax1dude.eaglercraft.v1_8.Base64;
import net.lax1dude.eaglercraft.v1_8.EagUtils;
import net.lax1dude.eaglercraft.v1_8.internal.IBufferArrayGL;
import net.lax1dude.eaglercraft.v1_8.internal.IBufferGL;
import net.lax1dude.eaglercraft.v1_8.internal.IProgramGL;
import net.lax1dude.eaglercraft.v1_8.internal.IShaderGL;
import net.lax1dude.eaglercraft.v1_8.internal.ITextureGL;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformAssets;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformInput;
import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.ByteBuffer;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.v1_8.internal.buffer.IntBuffer;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;

/**
 * Copyright (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 *
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 *
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 *
 * (please read the 'LICENSE' file this repo's root directory for more info)
 *
 */
public class EarlyLoadScreen {

    public static final String loadScreen =
        "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cENlaGlIenJlU3pOVGN6a2M5ZCI/Pgo8eDp4bXBtZXRhIHhtbG5zOng9ImFkb2JlOm5zOm1ldGEvIiB4OnhtcHRrPSJYTVAgQ29yZSA0LjQuMC1FeGl2MiI+CiA8cmRmOlJERiB4bWxuczpyZGY9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMiPgogIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PSIiCiAgICB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIKICAgIHhtbG5zOnN0RXZ0PSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VFdmVudCMiCiAgICB4bWxuczpkYz0iaHR0cDovL3B1cmwub3JnL2RjL2VsZW1lbnRzLzEuMS8iCiAgICB4bWxuczpHSU1QPSJodHRwOi8vd3d3LmdpbXAub3JnL3htcC8iCiAgICB4bWxuczp0aWZmPSJodHRwOi8vbnMuYWRvYmUuY29tL3RpZmYvMS4wLyIKICAgIHhtbG5zOnhtcD0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wLyIKICAgeG1wTU06RG9jdW1lbnRJRD0iZ2ltcDpkb2NpZDpnaW1wOmVlODc0NzUwLWYyMTgtNGZhYi04ZmVkLTk3YjdiNTRlMTRmOSIKICAgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDo2ZjJlY2IyYi1lZDdlLTRiNDktYTlkZS03YmRlNTNlOWVjZDciCiAgIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDpjNDQ3M2M4Yy02MmJhLTQyYjctYWQ0Yi02MjE4ODNiOTM5NDgiCiAgIGRjOkZvcm1hdD0iaW1hZ2UvcG5nIgogICBHSU1QOkFQST0iMi4wIgogICBHSU1QOlBsYXRmb3JtPSJXaW5kb3dzIgogICBHSU1QOlRpbWVTdGFtcD0iMTYzOTc5MDc4MDQ5ODI0MSIKICAgR0lNUDpWZXJzaW9uPSIyLjEwLjI0IgogICB0aWZmOk9yaWVudGF0aW9uPSIxIgogICB4bXA6Q3JlYXRvclRvb2w9IkdJTVAgMi4xMCI+CiAgIDx4bXBNTTpIaXN0b3J5PgogICAgPHJkZjpTZXE+CiAgICAgPHJkZjpsaQogICAgICBzdEV2dDphY3Rpb249InNhdmVkIgogICAgICBzdEV2dDpjaGFuZ2VkPSIvIgogICAgICBzdEV2dDppbnN0YW5jZUlEPSJ4bXAuaWlkOjYxMDQ5ZjkxLTE0N2ItNDJjNy1hYzRhLWMyNmU0ZDIzZmEwNSIKICAgICAgc3RFdnQ6c29mdHdhcmVBZ2VudD0iR2ltcCAyLjEwIChXaW5kb3dzKSIKICAgICAgc3RFdnQ6d2hlbj0iMjAyMS0xMi0xN1QxNzoyNjoyMCIvPgogICAgPC9yZGY6U2VxPgogICA8L3htcE1NOkhpc3Rvcnk+CiAgPC9yZGY6RGVzY3JpcHRpb24+CiA8L3JkZjpSREY+CjwveDp4bXBtZXRhPgogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgCjw/eHBhY2tldCBlbmQ9InciPz4gG/WSAAAABmJLR0QAnQCdAJ2roJyEAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAB3RJTUUH5QwSARoUHogWFQAAB8dJREFUeNrt3Wtu4yAUQGG36hadRbqLzPxixDCAwYbESb4jVWqbGGPMubz8+Lrf7/cF+FC+FQEIABAAIABAAIAAAAEAAgAEAAgAEAAgAEAAgAAAAQACAAQACAAQACAAQACAAAABAAIABAAIABAAIABAAIAAAAEAAgAEAAgAEAAgAEAAgAAAAQACAAQACAAQACAAQACAAAABAAIABAAIABAAIAAIABAAIABAAIAAAAEAAgAEAAgAEAAgAEAAgADAa/OjCN6L39/f4v/WdV3WdVVIBHjfyn+73arfIYAu0FujghPgoyv/tm3L/X4n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";
    public static final String enableScreen =
        "iVBORw0KGgoAAAANSUhEUgAAAIAAAACACAYAAADDPmHLAAAACXBIWXMAAC4jAAAuIwF4pT92AAAEAklEQVR42u2dvXbjIBBG7T0+xw+gTp06v//LmE6dO/VR5a3wGZNh+BGSFeveJgkIBrDy8TGKds8/Pz/PExyW8/P55AY4MP9YgmNzmeeZVUABAA8AKADgAQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAY7LOI7fpQDX65VPtZCt18w5d7rdbigAbOgBxnE8DcPwJnnDMCTrNJlsUVcizTnj9HWxeVvINfN9y361OdTEk30551ZZt3PsvYDYxOSChoPQ6sJ21mRLBm61jY0lpy61gDKWNdfcNcv5wErWLbfPF88I9/s9WtayzopXS85YtPqcMeT23SqedV1pucal1V4iTUooV/IaWSfbWHU5JmkvpmzrsayaB9DqfJnVTpMff72sc869/WzVlcjjOI7mOOVYfBzfT05exLfT5pqae008a71Ly6tPASV79CfPylvFjpm+teLH+tXiF5nA2LOAUMpCibckWpPBUOJT20btFuDjyK8p+S45Z4fX+ti+LDb3pef62PosWbfkDbBW8mFPhB/gt8Vr7gG+kZK9+C/GM2+ArffnnKRHbT5gSdJoK0+ydrziGyCW115LolLxnHOr59q3lt89b6U8Czg4pgdI5bUtKY3VzfOclGBtTLVSmmqn1cdyC7Iud+5791KX1MLJDz3Mg2s59pK6sM/asdTmLrRx5pzjS+e+awWw9lstVeuv1/a10rqwT8sn5LQr8RzaMVfmKrR2qfnFjs57/puLS0nyoTZp0fL8XGq+ap8v4AES+3Msx74kN2/tmblewWoXPl9o+RykZH5/5hTQYv+y+vj084XcPHpJbHmt1s7yGbV1q+UBnHO/gnoZje2RmuzK/Vr2F3sWEF6TGkvutqH5CG08qTmk5u77tLyK5Qtq62rgxRA8AO8FHBkygQeHLQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAQAPACgA4AEABQA8AKAAgAcAFAC+3gNM03Tqum7VQSyN4dtvMdZDKcBWC9oqhr8JoIEHeDwep77vf5VJfL0vl9fLa/u+f+vPfx9eszSGNXZo5AH6vlcXW36gsqykrzViwAIPYL3r3nXd63v5m6i9J2+VaT8viWGNHZQbYE97+KdjHPIGKH0XPSyL7eXSjPk2YZlsN03Tq21OjLAs598ZggIT2MpMbW3IMICFN0Dsv4xpfUbfAvIAK9wAcOAtAMgDwJHzAIACAB4AUADAAwAKAHgAQAEADwAoAOABAAUAPACgAIAHABQA8ACAAgAeAFAAwAMACgB4AEABAA8AKADgAQAFADwAoACABwAUAPAAgAIAHgBQAMADAAoAeABAAQAPACgA4AEABQA8AKAAgAcAFADwANCe/0of1jQ8XY5YAAAAAElFTkSuQmCC";

    private static IBufferGL vbo = null;
    private static IProgramGL program = null;

    public static void paintScreen() {
        ITextureGL tex = _wglGenTextures();
        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

        ImageData img = PlatformAssets.loadImageFile(Base64.decodeBase64(loadScreen));
        ByteBuffer upload = PlatformRuntime.allocateByteBuffer(192 * 192 * 4);
        IntBuffer pixelUpload = upload.asIntBuffer();
        pixelUpload.put(img.pixels);
        pixelUpload.flip();
        _wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 192, 192, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixelUpload);

        FloatBuffer vertexUpload = upload.asFloatBuffer();
        vertexUpload.clear();
        vertexUpload.put(0.0f);
        vertexUpload.put(0.0f);
        vertexUpload.put(0.0f);
        vertexUpload.put(1.0f);
        vertexUpload.put(1.0f);
        vertexUpload.put(0.0f);
        vertexUpload.put(1.0f);
        vertexUpload.put(0.0f);
        vertexUpload.put(0.0f);
        vertexUpload.put(1.0f);
        vertexUpload.put(1.0f);
        vertexUpload.put(1.0f);
        vertexUpload.flip();

        vbo = _wglGenBuffers();
        _wglBindBuffer(GL_ARRAY_BUFFER, vbo);
        _wglBufferData(GL_ARRAY_BUFFER, vertexUpload, GL_STATIC_DRAW);

        PlatformRuntime.freeByteBuffer(upload);

        IShaderGL vert = _wglCreateShader(GL_VERTEX_SHADER);
        _wglShaderSource(vert, "#version 300 es\nprecision lowp float; in vec2 a_pos; out vec2 v_pos; void main() { gl_Position = vec4(((v_pos = a_pos) - 0.5) * vec2(2.0, -2.0), 0.0, 1.0); }");
        _wglCompileShader(vert);

        IShaderGL frag = _wglCreateShader(GL_FRAGMENT_SHADER);
        _wglShaderSource(frag, "#version 300 es\nprecision lowp float; in vec2 v_pos; out vec4 fragColor; uniform sampler2D tex; uniform vec2 aspect; void main() { fragColor = vec4(texture(tex, clamp(v_pos * aspect - ((aspect - 1.0) * 0.5), 0.02, 0.98)).rgb, 1.0); }");
        _wglCompileShader(frag);

        program = _wglCreateProgram();

        _wglAttachShader(program, vert);
        _wglAttachShader(program, frag);
        _wglBindAttribLocation(program, 0, "a_pos");
        _wglLinkProgram(program);
        _wglDetachShader(program, vert);
        _wglDetachShader(program, frag);
        _wglDeleteShader(vert);
        _wglDeleteShader(frag);

        _wglUseProgram(program);
        _wglUniform1i(_wglGetUniformLocation(program, "tex"), 0);

        int width = PlatformInput.getWindowWidth();
        int height = PlatformInput.getWindowHeight();
        float x, y;
        if (width > height) {
            x = (float) width / (float) height;
            y = 1.0f;
        } else {
            x = 1.0f;
            y = (float) height / (float) width;
        }

        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);

        _wglViewport(0, 0, width, height);
        _wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        _wglClear(GL_COLOR_BUFFER_BIT);

        _wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);

        IBufferArrayGL vao = _wglGenVertexArrays();
        _wglBindVertexArray(vao);
        _wglEnableVertexAttribArray(0);
        _wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
        _wglDrawArrays(GL_TRIANGLES, 0, 6);
        _wglDisableVertexAttribArray(0);

        PlatformInput.update();
        EagUtils.sleep(50l); // allow webgl to flush

        _wglUseProgram(null);
        _wglBindBuffer(GL_ARRAY_BUFFER, null);
        _wglBindTexture(GL_TEXTURE_2D, null);
        _wglDeleteTextures(tex);
        _wglDeleteVertexArrays(vao);
    }

    public static void paintEnable() {
        ITextureGL tex = _wglGenTextures();
        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        ImageData img = PlatformAssets.loadImageFile(Base64.decodeBase64(enableScreen));
        IntBuffer upload = PlatformRuntime.allocateIntBuffer(128 * 128);
        upload.put(img.pixels);
        upload.flip();
        _wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 128, 128, 0, GL_RGBA, GL_UNSIGNED_BYTE, upload);

        PlatformRuntime.freeIntBuffer(upload);

        _wglUseProgram(program);

        int width = PlatformInput.getWindowWidth();
        int height = PlatformInput.getWindowHeight();
        float x, y;
        if (width > height) {
            x = (float) width / (float) height;
            y = 1.0f;
        } else {
            x = 1.0f;
            y = (float) height / (float) width;
        }

        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);

        _wglViewport(0, 0, width, height);
        _wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        _wglClear(GL_COLOR_BUFFER_BIT);

        _wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);

        IBufferArrayGL vao = _wglGenVertexArrays();
        _wglBindVertexArray(vao);
        _wglBindBuffer(GL_ARRAY_BUFFER, vbo);
        _wglEnableVertexAttribArray(0);
        _wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
        _wglDrawArrays(GL_TRIANGLES, 0, 6);
        _wglDisableVertexAttribArray(0);

        PlatformInput.update();
        EagUtils.sleep(50l); // allow webgl to flush

        _wglUseProgram(null);
        _wglBindBuffer(GL_ARRAY_BUFFER, null);
        _wglBindTexture(GL_TEXTURE_2D, null);
        _wglDeleteTextures(tex);
        _wglDeleteVertexArrays(vao);
    }

    public static void paintFinal(byte[] image) {
        ITextureGL tex = _wglGenTextures();
        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
        _wglTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
        ImageData img = PlatformAssets.loadImageFile(image);
        IntBuffer upload = PlatformRuntime.allocateIntBuffer(256 * 256);
        upload.put(img.pixels);
        upload.flip();
        _wglTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, 256, 256, 0, GL_RGBA, GL_UNSIGNED_BYTE, upload);

        PlatformRuntime.freeIntBuffer(upload);

        _wglUseProgram(program);

        int width = PlatformInput.getWindowWidth();
        int height = PlatformInput.getWindowHeight();
        float x, y;
        if (width > height) {
            x = (float) width / (float) height;
            y = 1.0f;
        } else {
            x = 1.0f;
            y = (float) height / (float) width;
        }

        _wglActiveTexture(GL_TEXTURE0);
        _wglBindTexture(GL_TEXTURE_2D, tex);

        _wglViewport(0, 0, width, height);
        _wglClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        _wglClear(GL_COLOR_BUFFER_BIT);

        _wglUniform2f(_wglGetUniformLocation(program, "aspect"), x, y);

        IBufferArrayGL vao = _wglGenVertexArrays();
        _wglBindVertexArray(vao);
        _wglBindBuffer(GL_ARRAY_BUFFER, vbo);
        _wglEnableVertexAttribArray(0);
        _wglVertexAttribPointer(0, 2, GL_FLOAT, false, 8, 0);
        _wglDrawArrays(GL_TRIANGLES, 0, 6);
        _wglDisableVertexAttribArray(0);

        PlatformInput.update();
        EagUtils.sleep(50l); // allow webgl to flush

        _wglUseProgram(null);
        _wglBindBuffer(GL_ARRAY_BUFFER, null);
        _wglBindTexture(GL_TEXTURE_2D, null);
        _wglDeleteTextures(tex);
        _wglDeleteVertexArrays(vao);
        _wglDeleteBuffers(vbo);
        _wglDeleteProgram(program);
    }
}
