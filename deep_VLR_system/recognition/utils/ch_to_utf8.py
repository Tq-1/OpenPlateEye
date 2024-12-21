# 导入os模块
import os
# 所有车牌名的第一个中文列表
chinese_list = ['京', '津', '沪', '渝', '冀', '晋', '蒙', '辽', '吉', '黑', '苏', '浙', '皖', '闽', '赣', '鲁', '豫', '鄂', '湘', '粤', '桂', '琼', '川', '贵', '云', '藏', '陕', '甘', '青', '宁', '新', '港', '澳', '台', '使', '领', '警', '学', 'O']
# 把该文件夹下的所有文件名前缀改成utf-8编码进行重命名
def rename_files_to_utf8(path):
    # 获取该目录下所有文件，存入列表中
    filelist = os.listdir(path)
    # 遍历所有文件
    for files in filelist:
        # 原来的文件路径
        Olddir = os.path.join(path, files)
        # 如果是文件
        if os.path.isfile(Olddir):
            # 文件名
            filename = os.path.splitext(files)[0]
            # 文件后缀
            filetype = os.path.splitext(files)[1]
            # 文件名转换成chinese_list中的中文对应的索引+_
            filename = list(filename)
            filename[0] = str(chinese_list.index(filename[0]))
            # 在filename[1]插入_
            filename.insert(1, '_')
            # 把filename转换成字符串
            filename = ''.join(filename)
            # 新的文件路径
            Newdir = os.path.join(path, filename + filetype)
            # 重命名
            os.rename(Olddir, Newdir)
        # 如果不是文件，递归这个文件夹的路径
        else:
            rename_files_to_utf8(Olddir)

if __name__ == '__main__':
    rename_files_to_utf8("E:/study2/大四学校课程内容/毕业设计/vehicle_license_recognition/Chinese_license_plate_detection_recognition-main/data/val_detect/challenge1")